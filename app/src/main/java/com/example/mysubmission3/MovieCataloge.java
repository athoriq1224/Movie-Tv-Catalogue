package com.example.mysubmission3;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;

public class MovieCataloge extends Application {
    public static final String MOVIE_DB_API_KEY = "0e9376d3746b166feec94cff1add7e93";
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API",message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);

        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
