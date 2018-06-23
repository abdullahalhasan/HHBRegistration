package com.droidsoftbd.hhbregistration.Classes;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import com.droidsoftbd.hhbregistration.R;

import java.util.Date;

/**
 * Created by BDDL-102 on 6/5/2018.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    int newDate;
    Date date;
    int year;
    int month;
    int day;
    public static int age;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        //final Calendar c = Calendar.getInstance();
        final Calendar c;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            date = new Date();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            newDate = c.get(Calendar.YEAR);
            Log.e("month",newDate+" "+year);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        EditText tv1 = (EditText) getActivity().findViewById(R.id.last_date_donate);
        tv1.setText("" + view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear());


    }
}