package com.text.huilv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FirstListActivity extends ListActivity {
    private static final String TAG = "RateActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first_list);
        /*String[]list_data={"1","2","3","4"};
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_data);
        setListAdapter(adapter);*/
        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);
        }

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==8){
                    ArrayList<String> list2=(ArrayList<String >)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(FirstListActivity.this,
                            android.R.layout.simple_list_item_1, list2);
                    setListAdapter(adapter);
                }
            }
        };
        MyTask task =new MyTask();
        task.setHandler(handler);
        Thread t=new Thread(task);
        t.start();

    }
}
