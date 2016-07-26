package com.kyriakosalexandrou.ampersandtest.ui.activities;

import android.support.v7.app.AppCompatActivity;

import retrofit.RestAdapter;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();
    private static final String BASE_URL = "http://";

    public static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(BASE_URL)
            .build();
}
