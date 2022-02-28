package com.smartworks.smartwork.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.smartworks.smartwork.base.config.SmartworkAPI;

import net.time4j.android.ApplicationStarter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by smartworksNR on 13/10/21.
 */

@SuppressLint("Registered")
public class SmartworkApp extends Application {

    public static final String CHANNEL_ID = "SMARTWORK_ALARM_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();
        //FirebaseApp.initializeApp(this);
        ApplicationStarter.initialize(this, true);
        createNotificationChannnel();
    }

    public static SmartworkAPI getApiSmartwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ppndev.my.id/").client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(SmartworkAPI.class);
    }


    public static OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .addInterceptor(getLoggingInterceptor())
                .build();
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (true) return HttpLoggingInterceptor.Level.BODY;
        else return HttpLoggingInterceptor.Level.NONE;
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());
        return httpLoggingInterceptor;
    }

    private void createNotificationChannnel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "ToDoListApp Alarm Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
