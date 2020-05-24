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

    public Player(int mp, int def, int agi, int partyNumber) {
        if(partyNumber == 1) {
            System.out.println("自分のキャラの名前を入力してください");
        }
        else {
            System.out.println("相手のキャラの名前を入力してください");
        }

        name = scanner.nextLine();
        int hp = Hash(name)*5;
        this.hp = hp;
        int str = Hash(name);
        this.str = str;
        this.mp = mp;
        this.def = def;
        int luck = Hash(name);
        this.luck = luck;
        this.agi = agi;
        this.partyNumber = partyNumber;
        this.MaxHp = hp;
        this.MaxMp = mp;
    }

    public int Hash(String name) {
        int i = GetNumber(name, 0);
        return i;
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

    public static Integer GetNumber(String name, Integer index) {	//ハッシュ値から値を出す
        try {
            byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
            String digest = String.format("%040x",new BigInteger(1,result));
            String hex = digest.substring(index*2,index*2+2);
            int hash = Integer.parseInt(hex,16);
            if(hash <= 50) {
                hash = 300;
            }
            else if(hash >= 500){
                hash = 300;
            }
            return hash/5;
        } catch(Exception e) {

        }
        return 0;

    }


}
