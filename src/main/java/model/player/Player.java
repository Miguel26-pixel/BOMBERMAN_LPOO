package model.player;

import gui.KeySet;
import model.entity.Bomber;

public class Player {
    int number;
    Bomber bomber;
    PlayerKeySet keyset;

    public Player(int number) {
        this.number = number;
    }

    public PlayerKeySet getKeyset() {
        return keyset;
    }

    public void setKeyset(PlayerKeySet keyset) {
        this.keyset = keyset;
    }


    public int getNumber() {
        return number;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }
}
