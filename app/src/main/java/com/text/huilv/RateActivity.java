package com.text.huilv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements  Runnable {
    EditText rmb;
    TextView result_;
    private static final String TAG = "RateActivity";
    public float dollarRate = 0.1503f;
    public float euroRate = 0.1266f;
    public float wonRate = 170.2708f;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rmb = findViewById(R.id.input_rmb);
        result_ = findViewById(R.id.result_);
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        wonRate = sharedPreferences.getFloat("won_rate", 0.0f);

        //k开启线程
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@Nullable Message msg) {
                if (msg.what == 6) {
                    String str = (String) msg.obj;
                    //Log.i(TAG, "handleMessage: str=" + str);
                    result_.setText(str);
                    Toast.makeText(RateActivity.this,"数据已更新",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
    }

    public void click(View btn) {
        String str = rmb.getText().toString();//获得数据
        Float M;
        if (str.length() == 0) {
            Toast.makeText(this, "请输入计算的金额", Toast.LENGTH_SHORT).show();
        } else {
            M = Float.valueOf(str);
            float r = 0.1f;
            double R1 = 0;
            result_.setText(String.valueOf(btn.getId()));
           // Log.i(TAG, "click: btn.getid()" + btn.getId());

            if (btn.getId() == R.id.btn_dollar) {
                r = dollarRate;
            } else if (btn.getId() == R.id.btn_euro) {
                r = euroRate;
            } else if (btn.getId() == R.id.btn_won) {
                r = wonRate;
            }
            R1 = r * M;
            result_.setText(String.format("%.2f", R1));


        }
    }

    public void onclick(View btn) {
        Intent config = new Intent(this, ConfigActivity.class);
        config.putExtra("dollar_key", dollarRate);
        config.putExtra("euro_key", euroRate);
        config.putExtra("won_key", wonRate);
        //Log.i(TAG, "onCreate: dollarRate=" + dollarRate);
        //Log.i(TAG, "onCreate: euroRate=" + euroRate);
       // Log.i(TAG, "onCreate: wonRate=" + wonRate);

        startActivityForResult(config, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.i(TAG, "onActivityResult: dollar" + requestCode);
        //Log.i(TAG, "onActivityResult: dollar1" + resultCode);
        if (requestCode == 1 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar", 0.1f);
            euroRate = bundle.getFloat("key_euro", 0.1f);
            wonRate = bundle.getFloat("key_won", 0.1f);
            //Log.i(TAG, "onActivityResult: doller3" + dollarRate);
            SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat("dollar_rate", dollarRate);
            editor.putFloat("euro_rate", euroRate);
            editor.putFloat("won_rate", wonRate);
            editor.apply();
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void run() {
        //Log.i(TAG, "run: run.....");
        /*try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.i(TAG, "run:  after 2s");*/
        /*URL url = null;
        try {
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i(TAG, "run: html=" + html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "run: ex="+e.toString());
        }*/

        /*        File input =new File("/tmp/input.html");
        Document doc =Jsoup.parse(input,"","");
        Element links = doc.select("");
        Element pngs=doc.select("");
        Element masthead=doc.select("").first();
        Element resultLinks =doc.select();*/
        try{ Document doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            //Log.i(TAG, "run: title="+doc.title());
            Elements tables=doc.getElementsByTag("table");
            Element table=tables.first();
            //Element table1=doc.getElementsByTag("table").first();等于上面两行
            Elements trs=table.getElementsByTag("tr");
            /*for (Element td : tds){
                Log.i(TAG, "run: td="+td);
                Log.i(TAG, "run: td.text"+td.text());
                Log.i(TAG, "run: td.html"+td.html());
            }*/
            for (Element tr : trs){
                Elements tds=tr.getElementsByTag("tr");
                if(tds.size()>0){
                        String strs=tds.get(0).text();
                        String val=tds.get(5).text();
                        Log.i(TAG, "run: val"+val);
                        Log.i(TAG, "run: str"+strs);

                    }

            }
            Elements e1=doc.select("body > section > div > div > article > table > tbody > tr:nth-child(8) > td:nth-child(2)");
            Elements e2=doc.select("body > section > div > div > article > table > tbody > tr:nth-child(27) > td:nth-child(2)");
            Log.i(TAG, "run11: 美元="+e2);
           /* Elements class1=table1.getElementsByClass("bz");
            for (Element td : class1){
                Log.i(TAG, "run: td="+td);
            }*/
        }catch (IOException e){
            e.printStackTrace();
            Log.i(TAG, "run: "+e.toString());
        }

            Message msg = handler.obtainMessage(6, "gkd");
            //msg.what=6;
            //msg.obj="Hello from run()";
            handler.sendMessage(msg);
        }


    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[ ] buffer = new char[ bufferSize];final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312" );
        while (true) {
        int rsz = in.read(buffer,0,buffer.length);
        if(rsz<0)
            break;
        out.append(buffer,0,rsz);
        }
        return  out.toString();
        }

}