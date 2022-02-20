package com.smartworks.smartwork.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.LoginActivity;
import com.smartworks.smartwork.MenuEmployee.EmployeeActivity;
import com.smartworks.smartwork.MenuEmployee.WorkActivity;
import com.smartworks.smartwork.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardAdminActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId") @BindView(R.id.txWaktu) TextView txwaktu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
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
        Intent intent = new Intent(this, DetailWorkActivity.class);
        intent.putExtra("name","Marketing");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.employee)
    void employeeClick(){
        Intent intent = new Intent(this, DetailWorkActivity.class);
        intent.putExtra("name","Salesman");
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.logout)
    void LogoutClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}