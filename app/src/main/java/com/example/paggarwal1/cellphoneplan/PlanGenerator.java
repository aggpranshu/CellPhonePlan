package com.example.paggarwal1.cellphoneplan;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private HashMap<String, ArrayList> callRatesPPM = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> callRatesPPS = new HashMap<String, ArrayList>();
    private ArrayList<String> listOfRates = new ArrayList<String>();
    private String typeOfPlan;

    PlanGenerator(){};

    PlanGenerator(Long ppsBill, Long ppmBill) {
        this.ppmBill = ppmBill;
        this.ppsBill = ppsBill;
/*
        this.lessThan30 = lessThan30;
        this.moreThan30 = moreThan30;*/
    }

   /* public Long getMinimumRates() throws JSONException {

        JSONObject jsonResponse;
        jsonResponse = new JSONObject(ListOfStrings.vodafoneList);
        JSONArray jsonData = jsonResponse.getJSONArray("data");

        for(int i = 0 ; i< jsonData.length(); i++){
            JSONObject jsonObject = jsonData.getJSONObject(i);

            if(jsonObject.getString("plan_type").equals("PPM")){
                if(Integer.valueOf(jsonObject.getString("call_rate"))<Integer.valueOf(minPPMRate))
                minPPMRate = jsonObject.getString("call_rate");

            }
            else if (jsonObject.getString("plan_type").equals("PPS")){

            }
            else
            {
                continue;
            }


        }

        return Long.valueOf(1);
    }*/

    public void minimumRate() throws JSONException {

        JSONObject jsonResponse;
        jsonResponse = new JSONObject(ListOfStrings.vodafoneList);
        JSONArray jsonData = jsonResponse.getJSONArray("data");

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);

            if (jsonObject.has("plan_type") && jsonObject.getString("plan_type").equals("PPM") && jsonObject.getString("recharge_validity").equals("28 Days")) {
                if (callRatesPPM.containsKey(jsonObject.getString("call_rate"))) {
                    list = callRatesPPM.get(jsonObject.getString("call_rate"));
                } else {
                    list = new ArrayList<>();
                }
                list.add(jsonObject.getString("recharge_value"));
                callRatesPPM.put(jsonObject.getString("call_rate"), list);
            } else if (jsonObject.has("plan_type") && jsonObject.getString("plan_type").equals("PPS") && jsonObject.getString("recharge_validity").equals("28 Days")) {
                if (callRatesPPS.containsKey(jsonObject.getString("call_rate"))) {
                    list = callRatesPPS.get(jsonObject.getString("call_rate"));

                } else {
                    list = new ArrayList<>();
                }
                list.add(jsonObject.getString("recharge_value"));
                callRatesPPS.put(jsonObject.getString("call_rate"), list);

            }
        }

        setCallRatesPPM(callRatesPPM);
        setCallRatesPPS(callRatesPPS);
        Log.i("callRates", callRatesPPM.toString());
        Log.i("callRates", callRatesPPS.toString());
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

    /*public List suggestPlan(Long lessThan30, Long moreThan30) throws JSONException {
        if (lessThan30 > moreThan30) {
            typeOfPlan = "PPS";
        } else {
            typeOfPlan = "PPM";
        }
        JSONObject jsonResponse;
        jsonResponse = new JSONObject(ListOfStrings.vodafoneList);
        JSONArray jsonData = jsonResponse.getJSONArray("data");

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            Log.i("boolean", String.valueOf(jsonObject.has("plan_type")));
            //    Log.i("value", jsonObject.getString("plan_type"));
            if (jsonObject.has("plan_type") && jsonObject.getString("plan_type").equals(typeOfPlan)) {
                list.add(jsonObject.toString(4));
                Log.i("hello", list.toString());
            }
        }
        return list;
    }*/

}

