package com.smartworks.smartwork.MenuEmployee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smartworks.smartwork.MenuAdmin.DashboardAdminActivity;
import com.smartworks.smartwork.MenuAdmin.DetailWorkActivity;
import com.smartworks.smartwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrainingActivity extends AppCompatActivity {

    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.titleTask)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        ButterKnife.bind(this);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(TrainingActivity.this, DashboardEmployeeActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.work)
    void workClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Workshop Mindset");
        intent.putExtra("ada", "engga");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.employee)
    void employeeClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Pelatihan Jurnalistik");
        intent.putExtra("ada", "engga");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.workshop)
    void workshopClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Workshop Mindset");
        intent.putExtra("ada", "engga");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.jurnalistik)
    void jurnalClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Pelatihan Jurnalistik");
        startActivity(intent);
    }
}