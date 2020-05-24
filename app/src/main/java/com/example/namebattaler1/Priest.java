package com.example.namebattaler1;

import java.util.Random;
public class Priest extends Player {
    boolean bo = false;
    boolean mahodake;

    public Priest(int partyNumber) {
        super(50, 10, 50, partyNumber);		//mp , def , agi , パーティーナンバー
    }

    public void Attack(Player aite) {	//攻撃メソッド　　　引数は攻撃対象

        Random random = new Random();	//どの攻撃にするか乱数で決めるための準備
        int ran = random.nextInt(4);	//どの攻撃にするか決めるための乱数
        int damage = 0;		//攻撃ダメージ
        int hp = MaxHp;
        int nokori = aite.getHp();		//相手のHP

        if(mahi == true) {		//自身が麻痺状態なら読み込む
            int mahikaku = (int)Math.round(Math.random() * 100);	//動けない確率
            if(mahikaku >= 75) {
                mahi();
                return;
            }
        }

        if(doku == true) {		//自身が毒状態なら読み込む
            int doku = doku();
            this.hp -= doku;
        }

        //以下、作戦実行時のそれぞれの処理

        if(GameManager.MagicYusen == true) {	//魔法を優先して使用する作戦
            if(mp >= 20) {
                ran = random.nextInt(3)+1;
            }
            else if(mp >= 10 && mp < 20) {
                ran = random.nextInt(2)+2;
            }
            else {
                ran = 0;
            }
        }

        else if(GameManager.MagicSetuyaku == true) {	//魔法を節約する作戦
            if(MaxMp == mp) {
                ran = random.nextInt(4);
            }
            else {
                ran = 0;
            }
        }

        else if(GameManager.Barance == true) {		//バランス使用する作戦(前回の攻撃方法とかぶらないようにする)
            switch(ran) {
                case 0:
                    ran = random.nextInt(3)+1;
                    if(mp < 20) {
                        ran = random.nextInt(2)+2;
                    }
                    break;
                case 1:
                    while(true) {
                        ran = random.nextInt(4);
                        if(ran == 1) {	//1でケースと被ったら1以外が出るまでやる
                        }
                        else {
                            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                                ran = 0;
                            }
                            break;
                        }
                    }
                case 2:
                    while(true) {
                        ran = random.nextInt(4);
                        if(ran == 2) {	//2でケースと被ったら2以外が出るまでやる
                        }
                        else {
                            if(mp < 20) {	//mpが20より下なら1にする
                                if(mp < 10) {
                                    ran = 0;
                                    break;
                                }
                                else {
                                    ran = random.nextInt(2);
                                    if(ran == 1) {
                                        ran = 3;
                                        break;
                                    }
                                }
                            }
                        }
                    }
            }

            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                ran = 0;
            }
        }

        else if(GameManager.Inochi == true) {	//命優先にする作戦
            if(mp >= 20 && hp != MaxHp) {
                ran = 1;
            }
            else {
                while(true) {
                    ran = random.nextInt(4);
                    if(ran != 1) {
                        break;
                    }
                }
                if(mp < 10) {
                    ran = 0;
                }
            }

        }

        else if(GameManager.Ransu == true) {	//ランダムに攻撃を選ぶ
            ran = random.nextInt(3);	//どの攻撃方法かを決める乱数
        }


        if(ran == 0) {			//通常攻撃
            boolean kaishin = Kaishin();	//会心の一撃が発動するかどうか
            damage = str;
            hp = aite.getHp();
            if(kaishin == false) {
                damage = aite.getDef()- damage;
            }
            else {
                damage = -damage;
            }

            if(damage > 0) {
                damage = -1;
            }
            damage *= -1;
            nokori = hp - damage;
            aite.setHp(nokori);
            System.out.println(name + "の攻撃！");
            if(kaishin == true) {
                System.out.println("会心の一撃！");
            }
            System.out.println(aite.getName() + "に" + damage + "のダメージ！");
        }

        else if(ran == 1){		//回復メソッド呼び出し
            System.out.println(name + "は魔法を唱えた");
            this.hp += 50;
            mp -= 20;
            if(MaxHp <= this.hp) {	//HPが上限を超えてしまったら上限の数値に設定する
                this.hp = MaxHp;
            }
            System.out.println(name + "は回復した");
            System.out.println(name + "のHPは" + this.hp);
        }

        else if(ran == 2){		//ポイズンメソッド
            mp -= 10;
            aite.setDoku(true);
            System.out.println(aite.getName() + "を毒にした！");
        }

        else if(ran == 3) {		//麻痺にするメソッド
            mp -= 10;
            aite.setMahi(true);
            System.out.println(aite.getName() + "を麻痺にした！");
        }

        System.out.println("残りMP"+mp);
    }
}
