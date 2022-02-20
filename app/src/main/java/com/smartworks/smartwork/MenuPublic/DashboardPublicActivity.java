package com.smartworks.smartwork.MenuPublic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MenuEmployee.DetailTrainingActivity;
import com.smartworks.smartwork.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardPublicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_public);
        ButterKnife.bind(this);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.marketing)
    void workClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Marketing");
        intent.putExtra("ada", "ada");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.salesman)
    void salesmanClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Salesman");
        intent.putExtra("ada", "ada");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bdng)
    void bdgClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Bandung Job Fair");
        intent.putExtra("ada", "ada");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.ispo)
    void ispoClick(){
        Intent intent = new Intent(this, DetailTrainingActivity.class);
        intent.putExtra("title", "Jakarta International Expo");
        intent.putExtra("ada", "ada");
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.logout)
    void LogoutClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}