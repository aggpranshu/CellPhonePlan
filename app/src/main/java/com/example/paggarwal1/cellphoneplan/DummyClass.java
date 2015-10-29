package com.example.paggarwal1.cellphoneplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PAggarwal1 on 10/27/2015.
 */
public class DummyClass {
    public HashMap<String, List> durationPlanMap = new HashMap<String, List>();

    public List<String> list28 = new ArrayList<String>();
    public List<String> list21 = new ArrayList<String>();
    public List<String> list20 = new ArrayList<String>();
    public List<String> list60 = new ArrayList<String>();

    DummyClass() {
        list28.add("9275547");
        list28.add("13088470");
        list28.add("13088479");
        list28.add("13088485");
        list28.add("13088488");
        list28.add("13088494");
        list28.add("19313136");
        list28.add("20591339");
        list28.add("22329472");
        list28.add("22329475");
        list28.add("22329485");
        list28.add("22329493");

        list21.add("13088744");
        list21.add("13088627");
        list21.add("13088640");

        list60.add("13088655");
        list60.add("13088492");

        list20.add("13088650");

        this.durationPlanMap.put("28", list28);
        this.durationPlanMap.put("21", list21);
        this.durationPlanMap.put("20", list20);
        this.durationPlanMap.put("60", list60);

    }

    public HashMap<String, List> getDurationPlanMap() {
        return durationPlanMap;
    }
}