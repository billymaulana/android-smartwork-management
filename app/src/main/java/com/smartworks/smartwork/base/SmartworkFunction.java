package com.smartworks.smartwork.base;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartworkFunction {
    Context context;
    private Calendar newCalendar;
    private Calendar newDate;
    private Date mSelectDate;
    private DatePickerDialog mEventDatePickerDialog;
    private int mYear, mMonth, mDate, mHour, mMinute;

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
                mYear = year;
                mMonth = monthOfYear;
                mDate = dayOfMonth;
                timePicker(etPreferredDate);
//                etPreferredDate.setTag(dateFormatterSend.format(newDate.getTime()));

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//            mEventDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            mEventDatePickerDialog.show();
        } else if (!mEventDatePickerDialog.isShowing()) {
            if (newCalendar == null) {
                newCalendar = Calendar.getInstance();
            }
            mEventDatePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {

                mYear = year;
                mMonth = monthOfYear;
                mDate = dayOfMonth;
                timePicker(etPreferredDate);
//                etPreferredDate.setTag(dateFormatterSend.format(newDate.getTime()));
            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//            mEventDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            mEventDatePickerDialog.show();
        }
    }

    private void timePicker(TextView etPreferredDate){
        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                mHour = hour;
                mMinute = minute;

                etPreferredDate.setText(mDate + "-" + mMonth + "-" + mYear + " " + mHour + ":" + mMinute);
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }
}
