package com.smartworks.smartwork.base;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartworkFunction {
    Context context;
    private Calendar newCalendar;
    private Calendar newDate;
    private Date mSelectDate;
    private DatePickerDialog mEventDatePickerDialog;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    //private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormatterSend = new SimpleDateFormat("yyyy-MMM");

    public SmartworkFunction(Context context){
        super();
        this.context = context;
    }

    public void showDatePicker(TextView etPreferredDate) {
        if (mEventDatePickerDialog == null) {
            if (newCalendar == null) {
                newCalendar = Calendar.getInstance();
            }
            mEventDatePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
                newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, monthOfYear);
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etPreferredDate.setText(dateFormatter.format(newDate.getTime()));
//                etPreferredDate.setTag(dateFormatterSend.format(newDate.getTime()));

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//            mEventDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            mEventDatePickerDialog.show();
        } else if (!mEventDatePickerDialog.isShowing()) {
            if (newCalendar == null) {
                newCalendar = Calendar.getInstance();
            }
            mEventDatePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {

                newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, monthOfYear);
                newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etPreferredDate.setText(dateFormatter.format(newDate.getTime()));
//                etPreferredDate.setTag(dateFormatterSend.format(newDate.getTime()));
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//            mEventDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            mEventDatePickerDialog.show();
        }
    }
}
