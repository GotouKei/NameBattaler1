package com.example.namebattaler1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

abstract class Player {
    Scanner scanner = new Scanner(System.in);
    String name;
    int hp;
    int mp;
    int str;
    int def;
    int luck;
    int agi;
    int partyNumber;
    boolean doku = false;
    boolean mahi = false;
    int MaxHp;
    int MaxMp;

    public Player(String name, int hp, int mp, int str, int def, int agi, int luck, int partyNumber) {
        this.name = name;
        this.hp = hp;
        this.str = str;
        this.mp = mp;
        this.def = def;
        this.luck = luck;
        this.agi = agi;
        this.partyNumber = partyNumber;
        this.MaxHp = hp;
        this.MaxMp = mp;
    }

    public boolean Kaishin() {
        boolean kaishin;

        int kaishinNumber = (int)Math.round(luck * Math.random());
        if(kaishinNumber <= 45) {
            kaishin = false;
        }
        else {
            kaishin = true;
        }
        return kaishin;
    }

    public String getName() {
        return name;
    }


    public int getHp() {
        return hp;

    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append(name+",");
        return string.toString();
    }

    abstract void Attack(Player aite) ;

    public int getAgi() {
        return agi;
    }

    public int getDef() {
        return def;
    }

    public int getPartyNumber() {
        return partyNumber;
    }

    public void setDoku(boolean doku) {
        this.doku = doku;
    }
    public boolean getDoku() {
        return doku;
    }

    public int doku() {
        int dokuDamage = 0;
        if(doku == true) {
            dokuDamage = 20;
            System.out.println(name + "が" + dokuDamage + "の毒ダメージを受けた！");
        }
        return dokuDamage;
    }

    public void mahi() {
        if(mahi == true) {
            System.out.println("痺れて動けなかった！");
        }
    }

    public void setMahi(boolean mahi) {
        this.mahi = mahi;
    }

    public boolean getMahi() {
        return mahi;
    }



}
