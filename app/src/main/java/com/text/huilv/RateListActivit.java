package com.text.huilv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateListActivit extends AppCompatActivity implements AdapterView.OnItemClickListener   {
    private static final String TAG = "RateListActivit";
    ListView listView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        //button
        listView = findViewById(R.id.mylist);
        listView.setOnItemClickListener(this);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==9){
                    ArrayList<HashMap<String,String>> retList=(ArrayList<HashMap<String,String>>)msg.obj;
                     adapter = new MyAdapter(RateListActivit.this,R.layout.list_item,retList);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        MyTask2 task=new MyTask2();
        task.setHandler(handler);
        Thread t =new Thread(task);
        t.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition=listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");

        //删除数据
        adapter.remove(map);



       /* Intent config = new Intent(this, ConfigActivity2.class);
        Float c =Float.valueOf(detailStr);
        config.putExtra("Rate-key", c);
        Log.i(TAG, "onItemClick: Str="+c);
        startActivityForResult(config, 10);*/

    }
}