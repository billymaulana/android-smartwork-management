package com.smartworks.smartwork.MenuAdmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartworks.smartwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCandidateActivity extends AppCompatActivity {
    @Nullable
    @SuppressLint("NonConstantResourceId") @BindView(R.id.name) TextView name;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.gender) TextView gender;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.dob) TextView dob;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.email) TextView email;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.noHP) TextView nohp;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.alamat) TextView alamat;
    @SuppressLint("NonConstantResourceId") @BindView(R.id.imgcv)
    ImageView imgCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_candidate);
        ButterKnife.bind(this);

        name.setText("Name : " + getIntent().getStringExtra("nama"));
        gender.setText("Gender : " + getIntent().getStringExtra("jk"));
        dob.setText("BOD : " + getIntent().getStringExtra("tglultah"));
        email.setText("Email : " + getIntent().getStringExtra("email"));
        nohp.setText("No HP : " + getIntent().getStringExtra("nohp"));
        alamat.setText("Alamat : " + getIntent().getStringExtra("alamat"));

        String img = getIntent().getStringExtra("cv");
        CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(this)
                .load(img)
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable)
                        .error(R.drawable.ic_image_empty)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false))
                .into(imgCv);
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        Intent intent = new Intent(DetailCandidateActivity.this, DetailWorkActivity.class);
        startActivity(intent);
    }




}