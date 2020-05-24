package com.example.namebattaler1;

import android.util.Log;

public class MyListItem {
    protected int id;
    protected String name;


    public MyListItem(int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId(){
        Log.d("取得したID:", String.valueOf(id));
        return id;
    }

    public String getName(){
        return name;
    }

}
