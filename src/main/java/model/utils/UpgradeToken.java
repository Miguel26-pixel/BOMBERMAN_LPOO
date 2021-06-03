package model.utils;

public enum UpgradeToken {
    SPEED_UPGRADE(EntityToken.SPEED_UPGRADE),
    BOMB_UPGRADE(EntityToken.BOMB_UPGRADE),
    BLAST_UPGRADE(EntityToken.BLAST_UPGRADE),
    HEALTH_UPGRADE(EntityToken.HEALTH_UPGRADE);

    public EntityToken entityToken;

    UpgradeToken(EntityToken entityToken) {
        this.entityToken = entityToken;
    }
}
