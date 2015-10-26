package com.example.paggarwal1.cellphoneplan;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private ArrayList<String> list = new ArrayList<String>();
    private String typeOfPlan;

    PlanGenerator(Long ppsBill, Long ppmBill) {
        this.ppmBill = ppmBill;
        this.ppsBill = ppsBill;
        this.minPPSRate = "1.2";
        this.minPPMRate = "0.2";
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


    public List suggestPlan(Long lessThan30, Long moreThan30) throws JSONException {
        if (lessThan30 >  moreThan30) {
            typeOfPlan = "PPS";
        }
        else
        {
            typeOfPlan = "PPM";
        }
        JSONObject jsonResponse;
        jsonResponse = new JSONObject(ListOfStrings.vodafoneList);
        JSONArray jsonData = jsonResponse.getJSONArray("data");

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject jsonObject = jsonData.getJSONObject(i);
            Log.i("boolean",String.valueOf(jsonObject.has("plan_type")));
        //    Log.i("value", jsonObject.getString("plan_type"));
            if (jsonObject.has("plan_type") && jsonObject.getString("plan_type").equals(typeOfPlan)) {
                list.add(jsonObject.toString(4));
                Log.i("hello",list.toString());
            }
        }
        return list;
    }

}

