package com.example.namebattaler1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class KyaraKanryo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyara_kanryo);

        Intent intent = getIntent();

        String strName = intent.getStringExtra("KEY_NAME");
        String strJob = intent.getStringExtra("KEY_JOB");
        String strHp = intent.getStringExtra("KEY_HP");
        String strMp = intent.getStringExtra("KEY_MP");
        String strStr = intent.getStringExtra("KEY_STR");
        String strDef = intent.getStringExtra("KEY_DEF");
        String strAgi = intent.getStringExtra("KEY_AGI");
        String strLuck = intent.getStringExtra("KEY_LUCK");

        TextView tName = findViewById(R.id.mName);
        TextView tJob = findViewById(R.id.mJob);
        TextView tHp = findViewById(R.id.mHp);
        TextView tMp = findViewById(R.id.mMp);
        TextView tStr = findViewById(R.id.mStr);
        TextView tDef = findViewById(R.id.mDef);
        TextView tAgi = findViewById(R.id.mAgi);
        TextView tLuck = findViewById(R.id.mLuck);

        tName.setText(strName);
        tJob.setText(strJob);
        tHp.setText(strHp);
        tMp.setText(strMp);
        tStr.setText(strStr);
        tDef.setText(strDef);
        tAgi.setText(strAgi);
        tLuck.setText(strLuck);
    }

    public void kyara_sakusei3(View v){
        Intent intent = new Intent(this,KyaraSakusei.class);
        startActivity(intent);
    }

    public void kyara_ichiran2(View v){
        Intent intent = new Intent(this,KyaraIchiran.class);
        startActivity(intent);
    }



}