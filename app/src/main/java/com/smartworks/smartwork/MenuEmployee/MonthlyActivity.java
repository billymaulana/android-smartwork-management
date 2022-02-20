package com.smartworks.smartwork.MenuEmployee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.smartworks.smartwork.MenuEmployee.Adapter.ListTaskAdapter;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.work.WorkResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyActivity extends AppCompatActivity {
    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;

    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.rv_done) RecyclerView rvDone;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.rv_onGoing) RecyclerView rvGoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);
        getTask();
        getTaskSuscces();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
       finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.home)
    public void HomeClick(){
        Intent intent = new Intent(MonthlyActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }

    private void getTask(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().GetTask(smartworkPreference.getToken(), "Processing").enqueue(new Callback<WorkResponse>() {
            @Override
            public void onResponse(Call<WorkResponse> call, Response<WorkResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        if (response.body().getDataWorkResponses() == null){

                            rvGoing.setVisibility(View.GONE);
                        }else {

                            rvGoing.setVisibility(View.VISIBLE);
                            System.out.println("ini bestie");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MonthlyActivity.this);
                            rvGoing.setLayoutManager(linearLayoutManager);
                            ListTaskAdapter listTaskAdapter = new ListTaskAdapter(MonthlyActivity.this, response.body().getDataWorkResponses());
                            rvGoing.setAdapter(listTaskAdapter);
                            listTaskAdapter.notifyDataSetChanged();
                        }
                    }else {
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(MonthlyActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkResponse> call, Throwable t) {
                new SweetAlertDialog(MonthlyActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }
    private void getTaskSuscces(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().GetTask(smartworkPreference.getToken(), "Success").enqueue(new Callback<WorkResponse>() {
            @Override
            public void onResponse(Call<WorkResponse> call, Response<WorkResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        if (response.body().getDataWorkResponses() == null){
                            rvDone.setVisibility(View.GONE);
                        }else {

                            rvDone.setVisibility(View.VISIBLE);
                            System.out.println("ini bestie");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MonthlyActivity.this);
                            rvDone.setLayoutManager(linearLayoutManager);
                            ListTaskAdapter listTaskAdapter = new ListTaskAdapter(MonthlyActivity.this, response.body().getDataWorkResponses());
                            rvDone.setAdapter(listTaskAdapter);
                            listTaskAdapter.notifyDataSetChanged();
                        }
                    }else {
                        //new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(MonthlyActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkResponse> call, Throwable t) {
                new SweetAlertDialog(MonthlyActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }
}