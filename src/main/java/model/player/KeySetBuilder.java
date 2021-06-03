package model.player;

import gui.GUI;
import gui.Key;

public class KeySetBuilder {
    public KeySetBuilder() {
    }

    public PlayerKeySet buildKeySet(int nPlayer){
        PlayerKeySet keySet = new PlayerKeySet();
        if(nPlayer == 1){
            keySet.addKey(Key.A, GUI.ACTION.LEFT);
            keySet.addKey(Key.S, GUI.ACTION.DOWN);
            keySet.addKey(Key.D, GUI.ACTION.RIGHT);
            keySet.addKey(Key.W, GUI.ACTION.UP);
            keySet.addKey(Key.SPACEBAR, GUI.ACTION.PLACEBOMB);
            keySet.addKey(Key.Q, GUI.ACTION.QUIT);
            return keySet;


        } else if(nPlayer == 2){
            keySet.addKey(Key.ARROW_LEFT, GUI.ACTION.LEFT);
            keySet.addKey(Key.ARROW_RIGHT, GUI.ACTION.RIGHT);
            keySet.addKey(Key.ARROW_UP, GUI.ACTION.UP);
            keySet.addKey(Key.ARROW_DOWN, GUI.ACTION.DOWN);
            keySet.addKey(Key.ENTER, GUI.ACTION.PLACEBOMB);
            keySet.addKey(Key.Q, GUI.ACTION.QUIT);
            return keySet;
        }
        return null;
    }

}
