package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v) {
        EditText editTextren = (EditText)findViewById(R.id.editTextren);
        EditText editTextmei = (EditText)findViewById(R.id.editTextmei);
        EditText editTexthui = (EditText)findViewById(R.id.editTexthui);
        double R1 = 0;
        double M;
        double Huan;
        String a;
        String s;
        String b;
        if (v.getId()==R.id.button){
            a=editTextren.getText().toString();
            if("".equals(a)){
                R1 = 0;
            }
            else{

                R1 =Float.valueOf(a);

            }
            b=editTexthui.getText().toString();
            if("".equals(b)){
                Huan = 0;
            }
            else{

                Huan =Float.valueOf(b);

            }
            if (Huan==0){
                Toast.makeText(MainActivity.this,"输入有误",Toast.LENGTH_SHORT).show();
            }
            else {
                M=R1/Huan;
                editTextmei.setText(M+"");
            }

        }
        else{
            a=editTextmei.getText().toString();
            if("".equals(a)){
                M = 0;
            }
            else{

                M =Float.valueOf(a);

            }
            b=editTexthui.getText().toString();
            if("".equals(b)){
                Huan = 0;

            }
            else{

                Huan =Float.valueOf(b);

            }
            if (Huan==0){
                Toast.makeText(MainActivity.this,"输入有误",Toast.LENGTH_SHORT).show();
            }else {

                R1=M*Huan;
                editTextren.setText(R1+"");
            }
        }
    }
public void myclick(View v){
    Log.i(TAG, "myclick: 33333333");

    RateManager rateManager = new RateManager(this);
    RateItem item = new RateItem("CNY","1122.3");
    rateManager.add(item);

}
}