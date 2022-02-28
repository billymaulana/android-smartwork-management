package com.smartworks.smartwork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.alarm.AlarmService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(getIntent().getBooleanExtra("playNotifSound", false)){
            Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
            getApplicationContext().stopService(intentService);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnSignIn)
    void loginClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnSignUp)
    void regisClick(){
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }
}