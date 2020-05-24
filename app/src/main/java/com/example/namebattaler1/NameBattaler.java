package com.example.namebattaler1;

public class NameBattaler {

    public static void main(String [] args) {
        GameManager gm = new GameManager();	//進行役インスタン生成
        gm.prepareGame(); //ゲーム準備
        gm.startGame();	//ゲーム開始
    }

}
