package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ConfigActivity extends AppCompatActivity {
    private static final String TAG = "ConfigActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollar_key",0.0f);
        float euro2=intent.getFloatExtra("euro_key",0.0f);
        float won2=intent.getFloatExtra("won_key",0.0f);


        Log.i(TAG, "onCreate_: dollar2="+dollar2);
        Log.i(TAG, "onCreate: euro2="+euro2);
        Log.i(TAG, "onCreateq: won2="+won2);
    }
}