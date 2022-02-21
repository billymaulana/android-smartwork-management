package com.smartworks.smartwork.MenuEmployee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.SmartworkApp;
import butterknife.OnClick;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttedanceActivity extends AppCompatActivity {
    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attedance);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        finish();
    }

}