package com.example.namebattaler1;

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

        String strName = intent.getStringExtra("Text");
        System.out.println(strName);
        TextView tName = findViewById(R.id.mName);

        tName.setText(strName);
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
