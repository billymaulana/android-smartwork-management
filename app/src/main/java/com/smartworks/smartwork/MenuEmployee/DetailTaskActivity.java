package com.smartworks.smartwork.MenuEmployee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTaskActivity extends AppCompatActivity {

    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;


    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.titleTask) TextView title;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.date) TextView date;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.desc) TextView desc;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.btnAdd) TextView btnAdd;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.btndelete) TextView btndelete;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.btnEdit) TextView btnEdit;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.btnSuccess) TextView btnSuccess;


    String txTitle, txDesc, txDate,status,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        ButterKnife.bind(this);

        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);

        txTitle = getIntent().getStringExtra("nama");
        id = getIntent().getStringExtra("id");
        txDate = getIntent().getStringExtra("date");
        txDesc = getIntent().getStringExtra("desc");
        status = getIntent().getStringExtra("status");
        title.setText(txTitle);
        desc.setText(txDesc);
        date.setText("Deadline" + " " + txDate );

        if (status.equals("Success")){
            btnAdd.setVisibility(View.GONE);
            btndelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnSuccess.setVisibility(View.VISIBLE);
        }else{
            btnAdd.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            btnSuccess.setVisibility(View.GONE);

        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        finish();
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnEdit)
    public void EditClick(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("type","edit" );
        intent.putExtra("id",id );
        intent.putExtra("date",txDate );
        intent.putExtra("desc",txDesc );
        intent.putExtra("nama",txTitle );
        intent.putExtra("status",status );
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnAdd)
    public void UpdateClick(){
        updatetask();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btndelete)
    public void deleteClick(){
        deletetask();
    }

    private void updatetask(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().TaskUpdate(smartworkPreference.getToken(),"edit",txTitle,txDesc, "Success", txDate,id).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Sukses...")
                                .setContentText("Task Telah terupdate")
                                .setConfirmText("Oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent intent = new Intent(DetailTaskActivity.this, WorkActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }else {
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }

    private void deletetask(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().TaskDelete(smartworkPreference.getToken(),"delete", txDate, id).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Sukses...")
                                .setContentText("Task Telah terhapus")
                                .setConfirmText("Oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent intent = new Intent(DetailTaskActivity.this, WorkActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }else {
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                new SweetAlertDialog(DetailTaskActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }


}