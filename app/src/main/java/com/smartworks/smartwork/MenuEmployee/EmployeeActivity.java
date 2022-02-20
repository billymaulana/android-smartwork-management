package com.smartworks.smartwork.MenuEmployee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.MenuAdmin.DashboardAdminActivity;
import com.smartworks.smartwork.MenuPublic.DashboardPublicActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralCountAbsenResponse;
import com.smartworks.smartwork.base.config.response.GeneralCountResponse;
import com.smartworks.smartwork.base.config.response.token.TokenResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.name) TextView name;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.count_task) TextView countTask;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.report) TextView report;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.performance) TextView performance;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.hitAbsen) TextView coutnabsen;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.tab_Report) RelativeLayout tabReport;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.tab_performa) RelativeLayout tabPerforma;

    private SmartworkPreference smartworkPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        ButterKnife.bind(this);
        smartworkPreference = new SmartworkPreference(this);
        name.setText(smartworkPreference.getName());
        checktoken();
        checkAbsen();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(EmployeeActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.home)
    public void HomeClick(){
        Intent intent = new Intent(EmployeeActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.performance)
    void perfomClick(){
        tabPerforma.setVisibility(View.VISIBLE);
        tabReport.setVisibility(View.GONE);
        performance.setBackgroundResource(R.drawable.btn_design_main);
        performance.setTextColor(Color.parseColor("#ffffff"));
        report.setTextColor(Color.parseColor("#000000"));
        report.setBackgroundResource(R.drawable.btn_design_white);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.report)
    void reportClick(){
        tabPerforma.setVisibility(View.GONE);
        tabReport.setVisibility(View.VISIBLE);
        report.setBackgroundResource(R.drawable.btn_design_main);
        report.setTextColor(Color.parseColor("#ffffff"));
        performance.setTextColor(Color.parseColor("#000000"));
        performance.setBackgroundResource(R.drawable.btn_design_white);


    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.monthly)
    void monthlyClick(){
        Intent intent = new Intent(this, MonthlyActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.Attedance)
    void AttedanceClick(){
//        Intent intent = new Intent(this, MonthlyActivity.class);
//        startActivity(intent);
    }


    private void checktoken(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().countTask(smartworkPreference.getToken()).enqueue(new Callback<GeneralCountResponse>() {
            @Override
            public void onResponse(Call<GeneralCountResponse> call, Response<GeneralCountResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        countTask.setText(response.body().getCount().toString());
                    }else {
                        //new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){
                    if(response.code()==500){
                        //new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralCountResponse> call, Throwable t) {
                new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }

    private void checkAbsen(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().countAbsen(smartworkPreference.getToken()).enqueue(new Callback<GeneralCountAbsenResponse>() {
            @Override
            public void onResponse(Call<GeneralCountAbsenResponse> call, Response<GeneralCountAbsenResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        String  absen = response.body().getCount().toString();
                        System.out.println("ini"+absen);
                        coutnabsen.setText(absen);
                    }else {
                        //new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){
                    if(response.code()==500){
                        //new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralCountAbsenResponse> call, Throwable t) {
                new SweetAlertDialog(EmployeeActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }
}