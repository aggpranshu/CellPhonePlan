package com.example.paggarwal1.cellphoneplan;/*
package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

*/
/**
 * Created by RGupta1 on 9/28/2015.
 *//*

public class MsgLogStats extends Activity {

    TextView textView;
    String[] projection=new String[]{
        "body" , "address","date" ,"type"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        getSMSDetails();

    }

    private void getSMSDetails() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("*********SMS History*************** :");
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri, projection, "type=2", null, null);
        String[] name=cursor.getColumnNames();
        int n=name.length;
        for(int j=0;j<n;j++)
        {
            Log.i("Raskat", name[j]);
        }
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"))
                        .toString();
             //   String number = cursor.getString(cursor.getColumnIndexOrThrow("person"))
               //         .toString();
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                        .toString();
                Date smsDayTime = new Date(Long.valueOf(date));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                        .toString();
                String typeOfSMS = null;

                    stringBuffer.append("\nPhone Number:--- "  + " \nMessage Type:--- "
                            + typeOfSMS + " \nMessage Date:--- " + smsDayTime
                            + " \nMessage Body:--- " + body);
                    stringBuffer.append("\n----------------------------------");
                    cursor.moveToNext();

                textView.setText(stringBuffer);
            }
            cursor.close();
        }
    }

}
*/
