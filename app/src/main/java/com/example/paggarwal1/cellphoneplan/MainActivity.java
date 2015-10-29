package com.example.paggarwal1.cellphoneplan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button dateButton, planDays;
    TextView serviceProvider;
    EditText phoneNumber;
    Spinner spinnerDuration;
    List<String> categories = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        categories.add("Days for plans");
        categories.add("28 days");
        categories.add("60 days");
        categories.add("90 days");

        spinnerDuration = (Spinner) findViewById(R.id.spinnerDuration);

        serviceProvider = (TextView) findViewById(R.id.phoneCarrier);
        TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        serviceProvider.setText(tMgr.getNetworkOperatorName());

        dateButton = (Button) findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        spinnerDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuration.setAdapter(dataAdapter);

    }

}

    /*private void initializeDate() {
        datePicker = (DatePicker) findViewById(R.id.calendarView);
    }

    private ArrayList initializeCalender() {


        //  calendarView = (CalendarView) findViewById(R.id.calendarView2);
        calendarView.setShowWeekNumber(false);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                // Date date1 = cal.getTime();
                String currentDate = dateFormat.format(new Date(view.getDate()));

                date.add(dayOfMonth);
                date.add(month);
                date.add(year);

                Toast.makeText(getApplicationContext(), currentDate, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();

            }
        });

        return date;

    }
*/







        /*b = (Button) findViewById(R.id.button);
        mesgCount = (TextView) findViewById(R.id.textview1);

        initializeCalender();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               getdetails();
            }
        });
    }

    private void initializeCalender() {
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setShowWeekNumber(true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }
*/






