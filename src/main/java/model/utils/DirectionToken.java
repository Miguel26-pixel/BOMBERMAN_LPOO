package model.utils;

public enum DirectionToken {
    LEFT(3), DOWN(0), UP(2), RIGHT(1), NONE(4);

    public final int value;
    DirectionToken(int value) {
        this.value = value;
    }
}
