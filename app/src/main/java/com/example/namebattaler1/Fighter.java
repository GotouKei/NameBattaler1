package com.example.namebattaler1;

public class Fighter extends Player {

    public Fighter(int partyNumber) {

        super(100, 30, 30, partyNumber);
    }

    protected void Attack(Player aite) {
        boolean kaishin = Kaishin();
        int damage = str;
        int hp = aite.getHp();

        if(kaishin == false) {
            damage = aite.getDef() - damage;
        }
        else {
            damage = -damage;
        }
        if(mahi == true) {
            int mahikaku = (int)Math.round(Math.random() * 100);
            if(mahikaku >= 75) {
                mahi();
                return;
            }
        }
        if(damage > 0) {
            damage = -1;
        }
        damage *= -1;
        int nokori = hp - damage;
        aite.setHp(nokori);
        System.out.println(name + "の攻撃！");
        if(kaishin == true) {
            System.out.println("会心の一撃！");
        }
        System.out.println(aite.getName() + "に" + damage + "のダメージ！");

        if(doku == true) {	//毒状態かどうか
            int doku = doku();
            this.hp -= doku;
        }
    }
}
