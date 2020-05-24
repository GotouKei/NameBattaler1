package com.example.namebattaler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void kyara_ichiran1(View v){
        Intent intent = new Intent(this, KyaraIchiran.class);
        startActivity(intent);
    }

    public void battale_chokuzen1(View v){
        Intent intent = new Intent(this, PartyHensei.class);
        startActivity(intent);
    }
}
