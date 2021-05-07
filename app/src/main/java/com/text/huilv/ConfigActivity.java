
package com.text.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {
    private static final String TAG = "ConfigActivity";
    EditText newDollar;
    EditText newEuro;
    EditText newWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollar_key",0.0f);
        float euro2=intent.getFloatExtra("euro_key",0.0f);
        float won2=intent.getFloatExtra("won_key",0.0f);
        Log.i(TAG, "onCreate: dollar2="+dollar2);
        Log.i(TAG, "onCreate: euro2="+euro2);
        Log.i(TAG, "onCreate: won2="+won2);
        newDollar=(EditText)findViewById(R.id.newDollar) ;
        newEuro=(EditText)findViewById(R.id.newEuro) ;
        newWon=(EditText)findViewById(R.id.newWon) ;

        newDollar.setText(String.valueOf(dollar2));
        newEuro.setText(String.valueOf(euro2));
        newWon.setText(String.valueOf(won2));

    }
    public void save(View btn){
        Log.i(TAG, "save:  ");
        float newdoller=Float.parseFloat(newDollar.getText().toString());
        float neweuro=Float.parseFloat(newEuro.getText().toString());
        float newwon=Float.parseFloat(newWon.getText().toString());
        Log.i(TAG, "save: newdollar"+newdoller);
        Log.i(TAG, "save: neweuro"+neweuro);
        Log.i(TAG, "save: newwon"+newwon);
        //String D = newDollar.getText().toString();
        //newDollar_ =Float.valueOf(D);
       // String E = newEuro.getText().toString();
        //newEuro_ =Float.valueOf(E);
        //String W = newWon.getText().toString();
       // newWon_ =Float.valueOf(W);
        Intent ret=getIntent();
        Bundle bdl=new Bundle();
        bdl.putFloat("key_dollar",newdoller);
        bdl.putFloat("key_euro",neweuro);
        bdl.putFloat("key_won",newwon);
        ret.putExtras(bdl);
        setResult(2,ret);
        finish();

    }

}