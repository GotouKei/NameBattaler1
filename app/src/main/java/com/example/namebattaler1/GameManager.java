package com.example.namebattaler1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameManager {


    Scanner scanner = new Scanner(System.in);
    Fighter fighter1 = new Fighter(1);  //コンストラクタ（ジョブ名、(str)、パーティの種類(partyNumber)）
    Fighter fighter2 = new Fighter(2);
    Wizard wizard1 = new Wizard(1);
    Wizard wizard2 = new Wizard(2);
    Priest priest1 = new Priest(1);
    Hero hero2 = new Hero(2);

    Sakusen magicyusen = new MagicYusen();  //魔法優先の作戦インスタンス
    Sakusen magicsetuyaku = new Magicsetuyaku();  //マジック優先の作戦インスタンス
    Sakusen inochi = new Inochi();   //命優先の作戦インスタンス
    Sakusen barance = new Barance();  //バランス重視の作戦インスタンス
    Sakusen ransu = new Ransu();

    Random random = new Random();

    static ArrayList<Player> hajime = new ArrayList<Player>();  //攻撃する順番を入れるリスト
    static ArrayList<Player> owari = new ArrayList<Player>();	//攻撃し終わったキャラを入れるリスト

    Party party1 = new Party(1);	//パーティ種類(引数はパーティの種類ナンバー)
    Party party2 = new Party(2);

    int a;	//現在、攻撃する順番の何番目かを格納する変数

    Player attacker;	//これから攻撃するキャラ
    Player player;

    static boolean MagicYusen;	//どの作戦を使うのかをboolean型で判定
    static boolean Barance;
    static boolean MagicSetuyaku;
    static boolean Inochi;
    static boolean Ransu;


    public void prepareGame() {

        party1.addPlayer(fighter1);		//パーティーの中にキャラクターを入れていく
        party1.addPlayer(wizard1);
        party1.addPlayer(priest1);
        party2.addPlayer(fighter2);
        party2.addPlayer(wizard2);
        party2.addPlayer(hero2);
    }

    public void startGame() {
        Player aite;	//攻撃対象

        int count1 = 0;		//パーティー1の生き残りキャラ数をカウントする
        int count2 = 0;

        int ran;	//どのキャラを攻撃するのか決める時に乱数を入れるための変数

        System.out.println("バトルを開始します！");
        System.out.println("パーティ１[" + party1 + "] 対 パーティ２[" + party2 + "]");	//それぞれのパーティーの名簿

        addMember(party1);		//攻撃する順番を決めるためのリストにパーティーメンバーを入れていく
        addMember(party2);

        int count = 0;	//何番目の人が攻撃しているのかを他のクラスからわかるようにする

        Collections.sort(hajime, new junban());		//素早さが早い順に並び替え

        while(true) {
            sakusen();		//作戦選択
            for(int i = 0; i < hajime.size(); i++) {
                attacker = hajime.get(i);	//素早さが早い人から攻撃者になる

                count = i;		//何番目の人が攻撃しているか取得

                while(true) {
                    ran = random.nextInt(hajime.size());	//攻撃対象を選択
                    aite = hajime.get(ran);
                    if(ran != i) {		//相手のパーティーのキャラになるまでループ
                        if(aite.getPartyNumber() != attacker.getPartyNumber()) {	//自分のパーティーナンバーと違ったら攻撃可
                            a = ran;	//現在、攻撃する順番の何番目かを格納する
                            break;
                        }
                    }
                }

                System.out.println(attacker.getName() + "のターン！");

                aite = hajime.get(ran);
                attacker.Attack(aite);	//攻撃者の攻撃メソッド呼び出し


                if(aite.getHp() > 0) {	//相手のHPが0より上なら
                    System.out.println(aite.getName() + "の残りHP：" +aite.getHp());
                    if(attacker.getDoku() == true) {
                        System.out.println(attacker.getName() + "の残りHP：" + attacker.getHp());
                    }
                }
                else if(aite.getHp() <= 0) {	//相手のHPが0以下なら
                    Player p = hajime.remove(ran);	//死んだキャラは攻撃者リストから取り除く
                    System.out.println(p.getName() + "は死んだ！");
                }
                if(attacker.getHp() <= 0) {		//攻撃者のHPが0以下なら
                    Player p = hajime.remove(count);	//死んだキャラは攻撃者リストから取り除く
                    System.out.println(p.getName() + "は死んだ！");
                }

                System.out.println("");
                System.out.print("生き残りキャラ：");
                for(int b = 0; b < hajime.size(); b++) {
                    System.out.print(hajime.get(b));	//攻撃者リストに載っているキャラは生き残っているキャラだから表示
                }
                System.out.println("");

                count1 = 0;		//パーティー1の生き残りキャラ数をリセット
                count2 = 0;

                for(int g = 0; g < hajime.size(); g++) {	//パーティーの生き残りメンバー数を確かめ、勝ち負け判定
                    player = hajime.get(g);
                    int h = player.getPartyNumber();

                    if( h == 1) {
                        count1++;
                    }
                    else if( h == 2) {
                        count2++;
                    }
                }
                if(count1 == 0) {
                    System.out.println("パーティ１の負け！");
                }
                else if(count2 == 0) {
                    System.out.println("パーティ2の負け！");
                }
            }
            if(count1 == 0 || count2 == 0) {
                break;
            }
        }
    }

    public void addMember(Party party) {
        for(int i = 0; i < party.getSize(); i++) {
            hajime.add(party.getMember(i)); //攻撃順番を決めるためにリストにパーティのメンバーを入れる
        }
    }
    public void sakusen() {
        boolean b = true;
        Scanner scanner = new Scanner(System.in);	//作戦を入力してもらう

        System.out.println("作戦を決めてください！");
        System.out.println("作戦なし(A) / 命優先(B) / 魔法節約(C) / 魔法優先(D) / ランダム(E)");

        while(b == true) {
            String string = scanner.nextLine();
            switch(string) {
                case "A":
                    b = false;
                    ransu.sakusen();
                    break;
                case "B":
                    b = false;
                    inochi.sakusen();
                    break;
                case "C":
                    b = false;
                    magicsetuyaku.sakusen();
                    break;
                case "D":
                    b = false;
                    magicyusen.sakusen();
                    break;
                case "E":
                    b = false;
                    ransu.sakusen();
                    break;
                default:
                    System.out.println("選択肢から選んでください！");
                    break;
            }
        }
    }

}

