package com.example.paggarwal1.cellphoneplan;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {


    TextView mesgCount, billDetails;
    StringBuffer stringBuffer = new StringBuffer();
    HashMap<String, CallLogs> map = new LinkedHashMap<String, CallLogs>();
    long dayDuration = 0, nightDuration = 0, countGreaterThan30 = 0, countLessThan30 = 0;

    String[] projectionCall = new String[]{
            CallLog.Calls.DATE,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE};
    private long payPerSecondBill = 0, payPerMinuteBill = 0;
    private int smsCount = 0;
    String[] projectionMesg = new String[]{
            "type"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesgCount = (TextView) findViewById(R.id.mesgTextView);
        billDetails = (TextView) findViewById(R.id.detailsTextView);

        Date d = (Date) getIntent().getSerializableExtra("date");
        String whenItHappened = getIntent().getStringExtra("whenItHappened");

        if (whenItHappened.equals("before")) {
            CallRecords(d);
            MesgRecords(d);
        } else {
            //  fetchCallRecordsFromPast(d,whenItHappened);
        }
        //  payPerSecond();
    }

    public void MesgRecords(Date d) {
        StringBuffer stringBuffer = new StringBuffer();
        Uri uri = Uri.parse("content://sms");
        Cursor curMesg = getContentResolver().query(uri, projectionMesg, "type=2", null, null);

        if (curMesg.moveToFirst()) {
            for (int i = 0; i < curMesg.getCount(); i++) {
                String date = curMesg.getString(curMesg.getColumnIndexOrThrow("date"));
                Date smsDayTime = new Date(Long.valueOf(date));
                if (smsDayTime.compareTo(d) > 0) {
                    smsCount++;
                }
                curMesg.moveToNext();
            }
            mesgCount.setText(String.valueOf(smsCount));
            curMesg.close();
        }
    }

    public void CallRecords(Date d) {

        String selection = "type = 2";
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor curCall = resolver.query(CallLog.Calls.CONTENT_URI, projectionCall, selection, null, null);
        while (curCall.moveToNext()) {
            String truncatedNumber = "";
            String number = curCall.getString(curCall.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = curCall.getString(curCall.getColumnIndex(CallLog.Calls.DURATION));

            String date = curCall.getString(curCall.getColumnIndex(CallLog.Calls.DATE));
            Date callDate = new Date(Long.valueOf(date));


            if (callDate.compareTo(d) > 0 && Integer.valueOf(duration) > 0) {
                if (number.length() > 10) {
                    truncatedNumber = number.substring((Math.abs(10 - number.length())), number.length());
                } else
                    truncatedNumber = number;


                if (map.containsKey(truncatedNumber)) {
                    CallLogs obj = map.get(truncatedNumber);
                    obj.setDuration(Integer.valueOf(duration), callDate);
                    map.put(truncatedNumber, obj);

                } else {
                    CallLogs object = new CallLogs(truncatedNumber, duration, callDate);
                    object.setDuration(Integer.valueOf(duration), callDate);
                    map.put(truncatedNumber, object);
                }
                if (Integer.valueOf(duration) < 30) {
                    ++countLessThan30;
                    payPerSecondBill += Integer.valueOf(duration) * 1.2;
                    payPerMinuteBill += 35;
                } else {
                    ++countGreaterThan30;
                    payPerSecondBill += Integer.valueOf(duration) * 1.2;
                    payPerMinuteBill += (Math.ceil(Integer.valueOf(duration) / 60)) * 35;
                }
            }
        }

        stringBuffer.append("\nPPM charges  " + payPerMinuteBill + "\nPPS chargres  " + payPerSecondBill + "\ncalls less than 30 secs  " + countLessThan30 +
                "\ncalls greater than 30 secs " + countGreaterThan30);

        curCall.close();

        for (Map.Entry<String, CallLogs> entry : map.entrySet()) {
            String key = entry.getKey();
            CallLogs callLogs = entry.getValue();
            dayDuration += callLogs.getDurationDay();
            nightDuration += callLogs.getDurationNight();
        }

        stringBuffer.append("\nDuration Day " + dayDuration + "\nDuration Night " + nightDuration);
        billDetails.setText(stringBuffer.toString());
    }
}
