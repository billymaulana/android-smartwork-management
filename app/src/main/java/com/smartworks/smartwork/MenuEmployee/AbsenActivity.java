package com.smartworks.smartwork.MenuEmployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.smartworks.smartwork.MenuEmployee.Adapter.ListTaskAdapter;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.response.GeneralResponse;
import com.smartworks.smartwork.base.config.response.work.WorkResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenActivity extends AppCompatActivity {

    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_kategori) Spinner etKategori;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.time) TextView time;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.date) TextView date;

    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);


        String [] values = {"Pilih Kehadiran","Hadir","Alfa","Izin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        etKategori.setAdapter(adapter);
        etKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hasil =  (String) parent.getItemAtPosition(position);
                if (hasil.equals("Hadir")){
                    kategori = "Hadir";
                }
                if(hasil.equals("Alfa")){
                    kategori = "Alfa";
                }
                if(hasil.equals("Izin")){
                    kategori = "Izin";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        date.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        time.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));

    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnAdd)
    public void Absen(){
        postAbsen();
    }


    private void postAbsen(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.show();
        SmartworkApp.getApiSmartwork().Absen(smartworkPreference.getToken(), kategori).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        new SweetAlertDialog(AbsenActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Sukses...")
                                .setContentText("Absen Berhasil..")
                                .setConfirmText("Oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent intent = new Intent(AbsenActivity.this, DashboardEmployeeActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }else {
                        new SweetAlertDialog(AbsenActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                    if(response.code()==500){
                        new SweetAlertDialog(AbsenActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                new SweetAlertDialog(AbsenActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("server error").show();

            }
        });
    }
}