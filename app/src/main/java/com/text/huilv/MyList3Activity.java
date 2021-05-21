package com.text.huilv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyList3Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyList3Activity";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list3);
        listView = findViewById(R.id.mylist3);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        //progressBar.setVisibility(View.GONE);
        //listView.setVisibility(View.VISIBLE);

        /*准备数据
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        for(int i=0; i<10; i++){
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("ItemTitle","Rate:"+i);
            map.put("ItemDetail","detail"+i);
            listItems.add(map);
        }
        适配器
        SimpleAdapter adapter=new SimpleAdapter(this,listItems,R.layout.list_item,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail});
        listView.setAdapter(adapter);*/

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==9){
                    ArrayList<HashMap<String,String>> retList=(ArrayList<HashMap<String,String>>)msg.obj;
                    /*
                    SimpleAdapter adapter=new SimpleAdapter(MyList3Activity.this,retList,R.layout.list_item,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                            );*/

                    MyAdapter adapter = new MyAdapter(MyList3Activity.this,R.layout.list_item,retList);
                    listView.setAdapter(adapter);
                    //切换显示
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };

        listView.setOnItemClickListener(this);

        MyTask2 task=new MyTask2();
        task.setHandler(handler);
        Thread t =new Thread(task);
        t.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.i(TAG, "onItemClick: ");
        Object itemAtPosition=listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick: titleStr="+titleStr);
        Log.i(TAG, "onItemClick: detailStr="+detailStr);

        /*TextView title=(TextView) view.findViewById(R.id.itemTitle);
        TextView detail=(TextView) view.findViewById(R.id.itemDetail);
        String title2= String.valueOf(title.getText());
        String detail2= String.valueOf(title.getText());
        Log.i(TAG, "onItemClick: title2="+title2);
        Log.i(TAG, "onItemClick: detail2="+detail2);*/

        Intent config = new Intent(this, ConfigActivity2.class);
        Float c =Float.valueOf(detailStr);
        config.putExtra("Rate-key", c);
        Log.i(TAG, "onItemClick: Str="+c);
        startActivityForResult(config, 10);

    }
}