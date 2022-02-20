package com.smartworks.smartwork.MenuEmployee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartworks.smartwork.MainMenu.RegistActivity;
import com.smartworks.smartwork.MenuPublic.ApplyActivity;
import com.smartworks.smartwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTrainingActivity extends AppCompatActivity {
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.titleTask) TextView title;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.button) TextView button;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.desc) TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_training);
        ButterKnife.bind(this);

        title.setText(getIntent().getStringExtra("title"));
        String check = getIntent().getStringExtra("ada");
        String data = getIntent().getStringExtra("title");
        if (check.equals("ada")){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }


        if (data.equals("Bandung Job Fair") || data.equals("Jakarta International Expo")){
            desc.setText("Berikut kami informasikan kegiatan job fair yang kami selenggarakan di era new normal yang akan di selenggarakan oleh SmartWork dan bekerjasama dengan Jobstreet.com. Yang rencananya akan kami selenggarakan di beberapa kota dan tentunya dengan memperhatikan protokol kesehatan sesuai dengan anjuran pemerintah, berikut jadwal event kami:\n" +
                    "\n" +
                    "SEMARANG\n" +
                    "25-26 Januari 2022 – Gd. Audirotium imam Barjo, UNDIP\n" +
                    "JAKARTA\n" +
                    "9-10 Februari 2022 – SMESCO\n" +
                    "YOGYAKARTA\n" +
                    "22-23 Februari 2022 – Gd. Graha Sabha Permana, UGM\n" +
                    "MALANG\n" +
                    "9-10 Maret 2022 – Gd. Sasana Krida, Univ. Negeri Malang\n" +
                    "BANDUNG\n" +
                    "22-28 Februari 2022- Gd. Sabuga, ITB\n" +
                    "SURABAYA\n" +
                    "13-14 Mei 2022 – DBL Arena\n" +
                    "MAKASSAR\n" +
                    "18-19 Mei 2022 – Gd. Baruga, UNHAS\n" +
                    "MEDAN \n" +
                    "3-4 Juni 2022 – UNILAD PLAZA\n" +
                    "PALEMBANG\n" +
                    "8-9 Juni 2022 – Pascasarjana UNSRI\n" +
                    "JAKARTA \n" +
                    "17-18 Juni 2022 – SMESCO");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnBack)
    public void BackClick(){
        finish();
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.button)
    void regisClick(){
        Intent intent = new Intent(this, ApplyActivity.class);
        startActivity(intent);
    }
}