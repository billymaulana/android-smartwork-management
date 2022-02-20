package com.smartworks.smartwork.MenuEmployee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardEmployeeActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.txWaktu)
    TextView txwaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_employee);
        ButterKnife.bind(this);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            txwaktu.setText("Good Morning!");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            txwaktu.setText("Good Afternoon!");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            txwaktu.setText("Good Evening!");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            txwaktu.setText("Good Night!");
        }

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.work)
    void workClick(){
        Intent intent = new Intent(this, WorkActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.employee)
    void employeeClick(){
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnTraining)
    void TrainingClick(){
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnAbsen)
    void AbsenClick(){
        Intent intent = new Intent(this, AbsenActivity.class);
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.logout)
    void LogoutClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}