package com.smartworks.smartwork.MenuEmployee;

import static com.smartworks.smartwork.alarm.AlarmBroadcastReceiver.TITLE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MenuAdmin.DashboardAdminActivity;
import com.smartworks.smartwork.MenuAdmin.DetailWorkActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.alarm.AlarmBroadcastReceiver;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.GeneralResponseLogin;

import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_task) EditText etTask;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_desc) EditText etDesc;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_tgl) TextView ettgl;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.btnAdd) TextView etbtn;

    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;
    String Action = "add";
    String status = "Processing";
    String txTitle, txDesc, txDate,statusnya,id, type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);

        txTitle = getIntent().getStringExtra("nama");
        txDesc = getIntent().getStringExtra("desc");
        txDate = getIntent().getStringExtra("date");
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        statusnya = getIntent().getStringExtra("status");

        if (type.equals("edit")){
            etTask.setText(txTitle);
            etDesc.setText(txDesc);
            ettgl.setText(txDate);
            etbtn.setText("Update Task");
        }else{
            etbtn.setText("Add Task");
        }

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_tgl)
    public void tglClick(){
        smartworkFunction.showDatePicker(ettgl);
        System.out.println("keklik");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnAdd)
    void addTaskClick(){
        if (type.equals("edit")){
            updatetask();
            System.out.println("edit");
        }else{
            addTask();
            System.out.println("add");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(AddTaskActivity.this, WorkActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.home)
    public void HomeClick(){
        Intent intent = new Intent(AddTaskActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }

    private void addTask(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        String[] tgl = ettgl.getText().toString().split(" ");

        // Get Date
        String[] tgl1 = tgl[0].split("-");
        int year = Integer.parseInt(tgl1[2]);
        int month = Integer.parseInt(tgl1[1]);
        int date = Integer.parseInt(tgl1[0]);

        // Get Time
        String[] tgl2 = tgl[1].split(":");
        int hour = Integer.parseInt(tgl2[0]);
        int minute = Integer.parseInt(tgl2[1]);

        SmartworkApp.getApiSmartwork().Task(smartworkPreference.getToken(),Action,etTask.getText().toString(),etDesc.getText().toString(), status, tgl[0]).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){

                        scheduleAlarm(AddTaskActivity.this, etTask.getText().toString(), year, month, date, hour, minute);

                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Sukses...")
                                .setContentText("Task Berhasil Dibuat!")
                                .setConfirmText("Oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent intent = new Intent(AddTaskActivity.this, WorkActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }else {
                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }

    private void updatetask(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().TaskUpdate(smartworkPreference.getToken(),"edit",etTask.getText().toString(),etDesc.getText().toString(), status, ettgl.getText().toString(),id).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Sukses...")
                                .setContentText("Task Telah terupdate")
                                .setConfirmText("Oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent intent = new Intent(AddTaskActivity.this, WorkActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }else {
                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                new SweetAlertDialog(AddTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }


    private void scheduleAlarm(Context context, String title, int year, int month, int date, int hour, int minute){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(context, AlarmBroadcastReceiver.class);
        alarmIntent.putExtra(TITLE, title);
        int id = new Random().nextInt(Integer.MAX_VALUE);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, id, alarmIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    alarmPendingIntent
            );
        }
    }

//    private void cancelAlarm(int reminderId) {
//        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), reminderId, intent, 0);
//        alarmPendingIntent.cancel();
//        alarmManager.cancel(alarmPendingIntent);
//    }

}