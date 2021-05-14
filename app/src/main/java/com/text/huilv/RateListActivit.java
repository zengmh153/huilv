package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RateListActivit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        //button
        List<String> list1=new ArrayList<String>();
        for(int i=1;i<100;i++){
            list1.add("stu"+i);
        }
        ListView listView=(ListView) findViewById(R.id.mylist);
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        //setListAdapter(adapter);
        listView.setAdapter(adapter);
    }
}