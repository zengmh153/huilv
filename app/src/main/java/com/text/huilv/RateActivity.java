package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {
    EditText rmb;
    TextView result_;
    private static final String TAG = "RateActivity";
    public float dollarRate=0.1503f;
    public float euroRate=0.1266f;
    public float wonRate=170.2708f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rmb = findViewById(R.id.input_rmb);
        result_ = findViewById(R.id.result_);
    }
    public void click(View btn){
        String str = rmb.getText().toString();//获得数据
        Float M;
        M =Float.valueOf(str);
        float r =0.1f;
        double R1 = 0;
        result_.setText(String.valueOf(btn.getId()));
        Log.i(TAG, "click: btn.getid()"+btn.getId());
        if(str.length()==0){
            Toast.makeText(this,"请输入计算的金额",Toast.LENGTH_SHORT).show();
        }else if(btn.getId()==R.id.btn_dollar){
            r=dollarRate;
        }else if(btn.getId()==R.id.btn_euro){
            r=euroRate;
        }else if (btn.getId()==R.id.btn_won){
            r=wonRate;}
            R1=r*M;
            result_.setText(String.format("%.2f",R1));


    }public void onclick(View btn){
        Intent intent=new Intent();
        intent.setClass(RateActivity.this,ConfigActivity.class);
        startActivityForResult(intent,6);
        Intent config= new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_key",0);
        config.putExtra("euro_key",0);
        config.putExtra("won_key",0);

        Log.i(TAG, "onCreate: dollarRate="+dollarRate);
        Log.i(TAG, "onCreate: euroRate="+euroRate);
        Log.i(TAG, "onCreate: wonRate="+wonRate);
    }


}
