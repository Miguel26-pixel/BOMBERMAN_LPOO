package model.utils;

public enum EntityToken {
    WALL('#'),
    EMPTY('-'),
    BOMBER('P'),
    MONSTER('{'),
    BLAST('*'),
    BOMB('+'),
    BLAST_UPGRADE('*'),
    SPEED_UPGRADE('}'),
    BOMB_UPGRADE('+'),
    HEALTH_UPGRADE('|');

    public final char label;
    EntityToken(char label) {
        this.label = label;
    }
}
