package gui;

public interface KeySet {
    boolean addKey(Key key, GUI.ACTION action);
    boolean removeKey(Key key);
    GUI.ACTION getAction(Key key);
}
