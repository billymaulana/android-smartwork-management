package com.smartworks.smartwork.MainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.smartworks.smartwork.MainActivity;
import com.smartworks.smartwork.MenuAdmin.DashboardAdminActivity;
import com.smartworks.smartwork.MenuEmployee.DashboardEmployeeActivity;
import com.smartworks.smartwork.MenuPublic.DashboardPublicActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.GeneralResponseLogin;
import com.smartworks.smartwork.base.config.response.token.TokenResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_email) EditText etEmail;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_pass) EditText etPass;

    private SmartworkPreference smartworkPreference;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        smartworkPreference = new SmartworkPreference(this);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnSignUp)
    void regisClick(){
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnLogin)
    void loginClick(){
        Login();
    }


    private void Login(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().login(etEmail.getText().toString(), etPass.getText().toString()).enqueue(new Callback<GeneralResponseLogin>() {
            @Override
            public void onResponse(Call<GeneralResponseLogin> call, Response<GeneralResponseLogin> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        smartworkPreference.saveToken(response.body().getToken());

                        checktoken();
//                            Intent intent = new Intent(LoginActivity.this, DashboardEmployeeActivity.class);
//                            startActivity(intent);
                    }else {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponseLogin> call, Throwable t) {
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }

    private void checktoken(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().checktoken(smartworkPreference.getToken()).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        System.out.println("ini boss" + response.body().getDataTokenRespon().getRole());
                        smartworkPreference.saveName(response.body().getDataTokenRespon().getNama());
                        if (response.body().getDataTokenRespon().getRole().equals("Employee")){
                            System.out.println("ini  boss");
                            Intent intent = new Intent(LoginActivity.this, DashboardEmployeeActivity.class);
                            startActivity(intent);
                        }
                        if (response.body().getDataTokenRespon().getRole().equals("Public")){
                            Intent intent = new Intent(LoginActivity.this, DashboardPublicActivity.class);
                            startActivity(intent);
                        }
                        if (response.body().getDataTokenRespon().getRole().equals("Admin")){
                            Intent intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                            startActivity(intent);
                        }

                    }else {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }
}