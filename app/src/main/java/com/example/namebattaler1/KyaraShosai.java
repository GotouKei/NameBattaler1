package com.example.namebattaler1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class KyaraShosai extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview3);

        Intent intent = getIntent();

        String strName = intent.getStringExtra("Text");

        TextView tName = findViewById(R.id.listView3);
        tName.setText(strName);
    }
}
