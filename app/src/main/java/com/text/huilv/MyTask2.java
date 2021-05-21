package com.text.huilv;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTask2 implements Runnable {
    private static final String TAG = "MyTask";
    Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
        try {
            Thread.sleep(500);
            Document doc = Jsoup.connect(" https://www.boc.cn/sourcedb/whpj/ ").get();

            Element table = doc.getElementsByTag("table").get(1);
            Elements trs = table.getElementsByTag("td");
            Log.i(TAG, "run: trs="+trs);
            for(int i = 0 ;i <trs.size(); i+=8){
                Element td1 = trs.get(i);
                Element td2=trs.get(i+5);
                String a=td1.text();
                String b=td2.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("ItemTitle",a);
                map.put("ItemDetail",b);
                listItems.add(map);}
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "run:" + e.toString());
        }
        Message msg = handler.obtainMessage(9, listItems);
        handler.sendMessage(msg);
    }
}