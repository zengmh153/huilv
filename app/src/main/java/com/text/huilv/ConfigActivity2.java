package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity2 extends AppCompatActivity {
    private static final String TAG = "ConfigActivity2";
    EditText inputrmb;
    TextView result;
    public float Rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config2);
        inputrmb = findViewById(R.id.inputrmb);
        result = findViewById(R.id.result3);
        Intent intent=getIntent();
        Rate=intent.getFloatExtra("Rate-key",0.0f);
        Log.i(TAG, "onCreate: Rate="+Rate);
    }
    public void onclick(View btn) {
        String str = inputrmb.getText().toString();//获得数据
        Log.i(TAG, "onclick: str="+str);
        Float M;
        double R1;
        M = Float.valueOf(str);
        if (str.length() == 0) {
            Toast.makeText(this, "请输入计算的金额", Toast.LENGTH_SHORT).show();
        } else {
            R1=(100/Rate)*M;
            Log.i(TAG, "onclick: R1="+R1);
            Log.i(TAG, "onclick: Rate="+Rate);
            Log.i(TAG, "onclick: M="+M);
            result.setText(String.format("%.2f", R1));
        }
    }
    }
