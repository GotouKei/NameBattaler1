package com.example.namebattaler1;

public class MyListItem {

    private String name;
    private int job;
    private int hp;
    private int mp;
    private int str;
    private int def;
    private int agi;
    private int luck;
    private int create_at;

    protected MyListItem(String name, int job, int hp, int mp, int str, int def, int agi, int luck, int create_at){
        this.name = name;
        this.job = job;
        this.hp = hp;
        this.mp = mp;
        this.str = str;
        this.def = def;
        this.agi = agi;
        this.luck = luck;
        this.create_at = create_at;
    }

    public String getName(){
        return name;
    }

    public int getJob(){
        return job;
    }

    public int getHp(){
        return hp;
    }

    public int getMp(){
        return mp;
    }

    public int getStr(){
        return str;
    }

    public int getDef() {
        return def;
    }

    public int getAgi(){
        return agi;
    }

    public int getLuck(){
        return luck;
    }

    public int getCreate_At(){
        return create_at;
    }
}
