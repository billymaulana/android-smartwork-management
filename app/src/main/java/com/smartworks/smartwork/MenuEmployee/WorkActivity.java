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
import android.widget.LinearLayout;

import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.MenuEmployee.Adapter.ListTaskAdapter;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.work.WorkResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkActivity extends AppCompatActivity {

    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;

    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.rv_done) RecyclerView rvDone;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.rv_onGoing) RecyclerView rvGoing;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.notask) ImageView noTask;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.nodone) ImageView noDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);
        getTask();
        getTaskSuscces();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(WorkActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnAdd)
    void AddClick(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("type","add" );
        intent.putExtra("id","kosong" );
        intent.putExtra("date","kosong" );
        intent.putExtra("desc","kosong" );
        intent.putExtra("nama","kosong" );
        intent.putExtra("status","kosong" );
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
                            noTask.setVisibility(View.VISIBLE);
                            rvGoing.setVisibility(View.GONE);
                        }else {
                            noTask.setVisibility(View.GONE);
                            rvGoing.setVisibility(View.VISIBLE);
                            System.out.println("ini bestie");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WorkActivity.this);
                            rvGoing.setLayoutManager(linearLayoutManager);
                            ListTaskAdapter listTaskAdapter = new ListTaskAdapter(WorkActivity.this, response.body().getDataWorkResponses());
                            rvGoing.setAdapter(listTaskAdapter);
                            listTaskAdapter.notifyDataSetChanged();
                        }
                    }else {
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkResponse> call, Throwable t) {
                new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

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
                            noDone.setVisibility(View.VISIBLE);
                            rvDone.setVisibility(View.GONE);
                        }else {
                            noDone.setVisibility(View.GONE);
                            rvDone.setVisibility(View.VISIBLE);
                            System.out.println("ini bestie");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WorkActivity.this);
                            rvDone.setLayoutManager(linearLayoutManager);
                            ListTaskAdapter listTaskAdapter = new ListTaskAdapter(WorkActivity.this, response.body().getDataWorkResponses());
                            rvDone.setAdapter(listTaskAdapter);
                            listTaskAdapter.notifyDataSetChanged();
                        }
                    }else {
                        //new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkResponse> call, Throwable t) {
                new SweetAlertDialog(WorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }


}