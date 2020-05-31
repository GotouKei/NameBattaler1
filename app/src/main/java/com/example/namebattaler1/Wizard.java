package com.example.namebattaler1;

import java.util.Random;

public class Wizard extends Player {

    Random random = new Random();
    boolean bo = false;

    public Wizard(String name, int job, int hp, int mp, int str, int def, int agi, int luck) {
        super(name, job, hp, mp, str, def, agi, luck);
    }

    protected void Attack(Player aite) {	//攻撃メソッド　　引数は攻撃対象
        int ran;	//どんな攻撃をするのかを乱数で決めるための変数
        int damage = 0;		//相手に与えるダメージ
        int nokori;		//相手の残りHP
        ran = random.nextInt(3);

        if(mahi == true) {		//麻痺状態か
            int mahikaku = (int)Math.round(Math.random() * 100);	//麻痺状態の時に痺れる確率
            if(mahikaku >= 75) {
                mahi();		//痺れたら麻痺メソッドを呼び出す(Player)
                return;
            }
        }

        if(doku == true) {		//毒状態ならダメージを喰らう
            int doku = doku();
            this.hp -= doku;
        }

        //以下、作戦実行時のそれぞれの処理

        if(GameManager.MagicYusen == true) {	//魔法を優先して使用する作戦
            if(mp >= 20) {
                ran = random.nextInt(2);
            }
            else {
                ran = 2;
            }
        }

        else if(GameManager.MagicSetuyaku == true) {	//魔法を節約する作戦
            if(MaxMp == mp) {
                ran = random.nextInt(3);
            }
            else {
                ran = 2;
            }
        }

        else if(GameManager.Barance == true) {		//バランス使用する作戦(前回の攻撃方法とかぶらないようにする)
            switch(ran) {
                case 0:
                    ran = random.nextInt(2)+1;
                    if(mp < 20) {
                        ran = 2;
                    }
                    break;
                case 1:
                    while(true) {
                        ran = random.nextInt(3);
                        if(ran == 1) {	//1でケースと被ったら1以外が出るまでやる
                        }
                        else {
                            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                                ran = 2;
                            }
                            break;
                        }
                    }
                case 2:
                    while(true) {
                        ran = random.nextInt(3);
                        if(ran == 2) {	//2でケースと被ったら2以外が出るまでやる
                        }
                        else {
                            if(mp < 20) {	//mpが20より下なら1にする
                                ran = 0;
                                break;
                            }
                        }
                    }
            }

            if(mp < 10) {	//MPが10よりしたなら魔法が使えないため通常攻撃
                ran = 2;
            }
        }

        else if(GameManager.Inochi == true) {
            ran = random.nextInt(3);	//どの攻撃方法かを決める乱数
            if(mp < 20) {
                while(true) {
                    if(ran == 1) {
                        ran = random.nextInt(3);
                    }
                    else {
                        if(ran == 0) {
                            ran = 2;
                        };
                        break;
                    }
                }
            }
            else if(mp < 10) {
                ran = 2;
            }

        }

        else if(GameManager.Ransu == true) {	//ランダムに攻撃を選ぶ
            ran = random.nextInt(3);	//どの攻撃方法かを決める乱数
            if(mp < 20) {
                while(true) {
                    if(ran == 1) {
                        random.nextInt(3);
                    }
                    else {
                        break;
                    }
                }
            }
            else if(mp < 10) {
                ran = 2;
            }
        }


        //以下、攻撃リスト
        if(ran == 0) {
            System.out.println(name + "は呪文を唱えた！");		//ファイヤー
            System.out.println("ファイヤー");
            damage = faia();
            hp = aite.getHp();
            nokori = hp - damage;
            aite.setHp(nokori);
        }

        else if(ran == 1){
            System.out.println(name + "は呪文を唱えた！");		//サンダー
            System.out.println("サンダー");
            damage = sanda();
            hp = aite.getHp();
            nokori = hp - damage;
            aite.setHp(nokori);
        }

        else if(ran == 2){		//通常攻撃
            boolean kaishin = Kaishin();	//会心の一撃が発動するかどうか
            damage = str;
            hp = aite.getHp();

            if(kaishin == false) {
                damage = aite.getDef() - damage;
            }
            else {
                damage = -damage;
            }

            if(damage > 0) {	//ダメージが0より上ということは防御の方が大きいという事になるので1ダメージ
                damage = -1;
            }
            damage *=  -1;
            nokori = hp - damage;
            System.out.println(name + "の攻撃！");

            if(kaishin == true) {
                System.out.println("会心の一撃！");
            }

            aite.setHp(nokori);
        }

        System.out.println(aite.getName() + "に" + damage + "のダメージ！");

        System.out.println("残りMP"+mp);
    }

    protected int faia() {		//ファイヤー攻撃
        int damage = random.nextInt(20) + 10;
        mp -= 10;
        return damage;
    }

    protected int sanda() {		//サンダー攻撃
        int damage = random.nextInt(20) + 10 ;
        mp -= 20;
        return damage;
    }
}
