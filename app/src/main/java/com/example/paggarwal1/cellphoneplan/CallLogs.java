package com.example.paggarwal1.cellphoneplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PAggarwal1 on 9/30/2015.
 */
public class CallLogs {
    private Long number;
    private int durationDay;
    private int durationNight;
    private Date date;
    private String networkProvider;
    private String callType;

    CallLogs(String number, String duration, Date date) {
        this.number = Long.valueOf(number);
        this.date = date;
        this.networkProvider = "Vodafone";
        this.callType = "local";
    }

    public String getNetworkProvider() { return networkProvider; }

    public void setNetworkProvider(String networkProvider) { this.networkProvider = networkProvider; }

    public int getDurationDay() { return durationDay; }

    public void setDurationDay(int durationDay) { this.durationDay = durationDay; }

    public int getDurationNight() {
        return durationNight;
    }

    public void setDurationNight(int durationNight) {
        this.durationNight = durationNight;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCallType() { return callType; }

    public void setCallType(String callType) { this.callType = callType; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public void setDuration(int duration, Date d) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        Date EndTime = null;
        Date CurrentTime = null;
        try {
            EndTime = dateFormat.parse("23:00");
            CurrentTime = dateFormat.parse(dateFormat.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (CurrentTime.after(EndTime)) {
            durationNight += duration;
        } else
            durationDay += duration;
    }


}
