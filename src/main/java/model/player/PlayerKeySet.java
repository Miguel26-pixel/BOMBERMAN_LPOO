package model.player;


import gui.GUI;
import gui.Key;
import gui.KeySet;

import java.util.HashMap;
import java.util.Map;

public class PlayerKeySet implements KeySet {
    Map<Key, GUI.ACTION> keyMap;

    public PlayerKeySet() {
        this.keyMap = new HashMap<>();
    }

    public boolean addKey(Key key, GUI.ACTION action){
        if(keyMap.get(key) == null) {
            keyMap.put(key, action);
            return true;
        } else {
            return false;
        }
    }
    public boolean removeKey(Key key){
        if(keyMap.get(key) != null){
            keyMap.remove(key);
            return true;
        } else {
            return false;
        }
    }

    public GUI.ACTION getAction(Key key){
        return keyMap.get(key);
    }

}
