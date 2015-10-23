package com.example.paggarwal1.cellphoneplan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by PAggarwal1 on 10/14/2015.
 */
public class RoamingClass extends Activity {
    TelephonyManager telephonyManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onServiceStateChanged(ServiceState serviceState) {
                super.onServiceStateChanged(serviceState);
                if (telephonyManager.isNetworkRoaming()) {
                    Toast.makeText(getApplicationContext(),"In Roaming",Toast.LENGTH_SHORT).show();
                } else {
                    // Not in Roaming
                    Toast.makeText(getApplicationContext(),"Not in Roaming",Toast.LENGTH_SHORT).show();
                }
                // You can also check roaming state using this
                if (serviceState.getRoaming()) {
                    // In Roaming
                } else {
                    // Not in Roaming
                }
            }
        };
    }
}
