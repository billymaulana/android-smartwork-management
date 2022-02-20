package com.smartworks.smartwork.MenuAdmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.MenuAdmin.Adapter.ListCandidateAdapter;
import com.smartworks.smartwork.MenuEmployee.Adapter.ListTaskAdapter;
import com.smartworks.smartwork.MenuEmployee.WorkActivity;
import com.smartworks.smartwork.MenuPublic.DashboardPublicActivity;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.candidate.CandidateResponse;
import com.smartworks.smartwork.base.config.response.work.WorkResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWorkActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId") @BindView(R.id.title_candidate)
    TextView candidate;
    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.rv_onGoing)
    RecyclerView rvGoing;


    private SmartworkPreference smartworkPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_work);
        ButterKnife.bind(this);
        smartworkPreference = new SmartworkPreference(this);
        candidate.setText("Candidates of " + getIntent().getStringExtra("name"));
        getCandindat();
    }

    private void getCandindat(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().Candidate(smartworkPreference.getToken()).enqueue(new Callback<CandidateResponse>() {
            @Override
            public void onResponse(Call<CandidateResponse> call, Response<CandidateResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                            System.out.println("ini bestie");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailWorkActivity.this);
                            rvGoing.setLayoutManager(linearLayoutManager);
                            ListCandidateAdapter listCandidateAdapter = new ListCandidateAdapter(DetailWorkActivity.this, response.body().getDataCandidateResponses());
                            rvGoing.setAdapter(listCandidateAdapter);
                            listCandidateAdapter.notifyDataSetChanged();
                    }else {
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(DetailWorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CandidateResponse> call, Throwable t) {
                new SweetAlertDialog(DetailWorkActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(DetailWorkActivity.this, DashboardAdminActivity.class);
        startActivity(intent);
    }

}