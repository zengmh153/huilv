package com.text.huilv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;
import android.util.Log;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class RateManager {
    private static final String TAG = "RateManager";
    private  DBHelper dbHelper;
    private  String TBNAME;

    public RateManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;


    }
    public void add(RateItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        long r = db.insert(TBNAME,null,values);
        Log.i(TAG, "add: ret = "+r);
        db.close();
    }
    public List<RateItem> listALL(){
        List<RateItem> ret = new ArrayList<RateItem>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query( TBNAME, null,null,null,null,null,null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        RateItem item = new RateItem();
                        item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                        item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                        item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                        ret.add(item);
                    }
                    cursor.close();
                }
        db.close();
        return  ret;
    }
}
