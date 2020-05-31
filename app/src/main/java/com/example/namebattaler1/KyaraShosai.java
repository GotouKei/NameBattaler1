package com.example.namebattaler1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KyaraShosai extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview3);

        Intent intent = getIntent();
        String strName;

        strName = intent.getStringExtra("KEY_NAME");
        String strJob = intent.getStringExtra("KEY_JOB");
        String strHp = intent.getStringExtra("KEY_HP");
        String strMp = intent.getStringExtra("KEY_MP");
        String strStr = intent.getStringExtra("KEY_STR");
        String strDef = intent.getStringExtra("KEY_DEF");
        String strAgi = intent.getStringExtra("KEY_AGI");
        String strLuck = intent.getStringExtra("KEY_LUCK");
        String strCreate_At = intent.getStringExtra("KEY_CREATE_AT");

        TextView tName = findViewById(R.id.mmName);
        TextView tJob = findViewById(R.id.mmJob);
        TextView tHp = findViewById(R.id.mmHp);
        TextView tMp = findViewById(R.id.mmMp);
        TextView tStr = findViewById(R.id.mmStr);
        TextView tDef = findViewById(R.id.mmDef);
        TextView tAgi = findViewById(R.id.mmAgi);
        TextView tLuck = findViewById(R.id.mmLuck);
        TextView tCreate_At = findViewById(R.id.mmCreate_At);

        tName.setText(strName);
        tJob.setText(strJob);
        tHp.setText(strHp);
        tMp.setText(strMp);
        tStr.setText(strStr);
        tDef.setText(strDef);
        tAgi.setText(strAgi);
        tLuck.setText(strLuck);
        tCreate_At.setText(strCreate_At);
    }

    public void deleteDate(View v){
        DBAdapter dbAdapter = new DBAdapter(this);
        dbAdapter.openDB();
        dbAdapter.selectDelete(String.valueOf(this));
        dbAdapter.closeDB();
    }

}
