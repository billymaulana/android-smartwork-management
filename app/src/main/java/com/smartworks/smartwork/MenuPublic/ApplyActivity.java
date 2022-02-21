package com.smartworks.smartwork.MenuPublic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fxn.pix.Pix;
import com.smartworks.smartwork.R;
import com.smartworks.smartwork.base.SmartworkApp;
import com.smartworks.smartwork.base.SmartworkFunction;
import com.smartworks.smartwork.base.SmartworkPreference;
import com.smartworks.smartwork.base.config.request.RequestApply;
import com.smartworks.smartwork.base.config.response.GeneralResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @BindView(R.id.et_dob) TextView etdob;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.rb_l) RadioButton rbl;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.rb_p) RadioButton rbp;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.iv_cv) ImageView ivcv;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.et_nama) EditText etnama;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.et_nohp) EditText etNohp;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.et_email) EditText etEmail;
    @Nullable @SuppressLint("NonConstantResourceId") @BindView(R.id.et_addres) EditText etAddres;
    public static final int CAMERA_CV = 111;

    private SmartworkFunction smartworkFunction;
    private SmartworkPreference smartworkPreference;
    String jk,cv, baseimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ButterKnife.bind(this);
        smartworkFunction = new SmartworkFunction(this);
        smartworkPreference = new SmartworkPreference(this);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_dob)
    public void tglClick(){
        smartworkFunction.showDatePicker(etdob);
        System.out.println("keklik");
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_l)
    void rbLChecked(){
        if(rbl.isChecked()){
            jk = "L";
        }else {
            jk = "P";
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_p)
    void rbpChecked(){
        if(rbp.isChecked()){
            jk = "P";
        }else {
            jk = "L";
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_cv)
    public void cvClick(){
        Pix.start(this, CAMERA_CV);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btnLogin)
    void saveClick(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("mohon tunggu");
        progressDialog.show();
//        RequestApply requestApply = new RequestApply(
//                smartworkPreference.getToken(),
//                etnama.getText().toString(),
//                jk,
//                etdob.getText().toString(),
//                etEmail.getText().toString(),
//                etNohp.getText().toString(),
//                etAddres.getText().toString(),
//                baseimg
//        );

        Map<String, String> request = new HashMap<>();
        request.put("token", smartworkPreference.getToken());
        request.put("nama", etnama.getText().toString());
        request.put("jk", jk);
        request.put("birthdate", etdob.getText().toString());
        request.put("email", etEmail.getText().toString());
        request.put("phone", etNohp.getText().toString());
        request.put("address", etAddres.getText().toString());
        request.put("cv", baseimg);

        SmartworkApp.getApiSmartwork().upload(request).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                try {
                    if(response.body().getStatus().equals("success")){
                        startActivity(new Intent(ApplyActivity.this, WebViewActivity.class));
                    }else{
                       // new SweetAlertDialog(ApplyActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(response.body().getMessage()).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                progressDialog.dismiss();
//                SisfobetaLog.printStackTrace(t);
//                new SweetAlertDialog(PengajuanKprActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(getString(R.string.null_internal_server_error)).show();
           }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CV) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            System.out.println("data Image : " + returnValue.get(0));
            cv = returnValue.get(0);
            Glide.with(ApplyActivity.this)
                    .load(cv)
                    .apply(new RequestOptions()
                            .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(false))
                    .into(ivcv);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(cv);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            baseimg = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            System.out.println("base64ktp" + baseimg);
        }
    }

}