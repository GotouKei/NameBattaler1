package com.example.namebattaler1;

import java.util.ArrayList;

public class Party {

    ArrayList<Player> members = new ArrayList();
    int partyNumber;
    Player player;
    Party(int i){
        partyNumber = i;
        members = new ArrayList();
    }

    public void addPlayer(Player player) {
        members.add(player);
    }

    public int getPartyNumber() {
        return partyNumber;
    }

    public Player getMember(int ran) {
        player = members.get(ran);
        return player;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        for(int i = 0; i < members.size(); i++) {
            string.append(members.get(i));
            string.append("");
        }
        return string.toString();
    }

    public int getSize() {
        int i  = members.size();
        return i;
    }
}
