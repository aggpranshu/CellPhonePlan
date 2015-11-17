package com.example.paggarwal1.cellphoneplan;

import android.telecom.Call;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by PAggarwal1 on 10/26/2015.
 */
public class PlanGenerator {

    static int k = 0;
    private Long ppsBill;
    private Long ppmBill;
    /* private Long lessThan30;
     private Long moreThan30;*/
    private String minPPSRate;
    private String minPPMRate;
    private ArrayList<String> list;



    PlanGenerator(){};

    public HashMap<String, ArrayList> getCallRatesPPM() {
        return callRatesPPM;
    }

    public void setCallRatesPPM(HashMap<String, ArrayList> callRatesPPM) {
        this.callRatesPPM = callRatesPPM;
    }

    public HashMap<String, ArrayList> getCallRatesPPS() {
        return callRatesPPS;
    }

    public void setCallRatesPPS(HashMap<String, ArrayList> callRatesPPS) {
        this.callRatesPPS = callRatesPPS;
    }

    private HashMap<String, Double> billsPlan = new HashMap<String, Double>();
    private HashMap<String, ArrayList> callRatesPPM = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> callRatesPPS = new HashMap<String, ArrayList>();
    private ArrayList<JSONObject> listOfPlans = new ArrayList<JSONObject>();
    private String typeOfPlan;

    PlanGenerator(Long ppsBill, Long ppmBill) {
        this.ppmBill = ppmBill;
        this.ppsBill = ppsBill;
/*
        this.lessThan30 = lessThan30;
        this.moreThan30 = moreThan30;*/
    }

    public HashMap<String,Double> minimumRate(HashMap<String,CallLogs> map) throws JSONException {
         JSONObject obj;
        int totalDuration=0;
        int durationLessThan30=0,durationMoreThan30=0;
        int countCall = 0;
        Double bill;

        PlanGeneratorList object = new PlanGeneratorList();
        listOfPlans = object.listOfPlans("28");

        for (Map.Entry<String, CallLogs> entry : map.entrySet()) {
            String key = entry.getKey();
            CallLogs callLogs = entry.getValue();
            totalDuration += (Integer.valueOf(callLogs.getDurationLessThan30())+Integer.valueOf(callLogs.getDurationMoreThan30()));
            countCall += callLogs.getTotalCalls();

        }

        Iterator iterator = listOfPlans.iterator();
        while(iterator.hasNext()){
            bill=0.0;
          obj = (JSONObject)iterator.next();
            if (obj.has("plan_type") && obj.getString("plan_type").equals("PPS")) {
                bill += (Double.valueOf(obj.getString("call_rate"))*totalDuration);
            }
            else if (obj.has("plan_type") && obj.getString("plan_type").equals("PPM")) {
                bill += (Integer.valueOf(obj.getString("call_rate"))*countCall);
            }

            billsPlan.put(obj.getString("id"),bill);
            Log.i("TOTALCALLS", String.valueOf(countCall));
        }

        return billsPlan;
    }

    public void typeOfPlan() {
        if (ppmBill > 1.5 * ppsBill) {
            typeOfPlan = "PPS";
        } else if (ppmBill < 1.5 * ppsBill) {
            typeOfPlan = "PPM";
        } else {
            //do the call ratio coding here
        }
    }

}

