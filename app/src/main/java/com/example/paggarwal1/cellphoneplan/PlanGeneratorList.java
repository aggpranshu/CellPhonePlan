package com.example.paggarwal1.cellphoneplan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by RGupta1 on 10/28/2015.
 */
public class PlanGeneratorList {

    HashMap<String, List> list1 = new HashMap<String, List>();

    ArrayList<JSONObject> listOfJSONObjects = new ArrayList<JSONObject>();

    public ArrayList<JSONObject> listOfPlans(String validity) throws JSONException {
        JSONObject jsonResponse;
        jsonResponse = new JSONObject(ListOfStrings.vodafoneList);
        JSONArray jsonData = jsonResponse.getJSONArray("data");

        list1 = new DummyClass().getDurationPlanMap();

        for (Map.Entry<String, List> entry : list1.entrySet()) {
            String key = entry.getKey();
            if (key.equals(validity)) {
                List listOfPlans = entry.getValue();

                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject jsonObject = jsonData.getJSONObject(i);
                    if ((listOfPlans.contains(jsonObject.getString("id")))) {
                        listOfJSONObjects.add(jsonObject);
                    }
                }

            }


        }

        return listOfJSONObjects;
    }
}

