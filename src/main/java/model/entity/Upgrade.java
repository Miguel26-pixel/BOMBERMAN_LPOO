package model.entity;

import model.utils.EntityToken;
import model.utils.Position;
import model.utils.UpgradeToken;

public class Upgrade extends StaticEntity{
    private final UpgradeToken upgradeToken;
    public Upgrade(Position position, UpgradeToken upgradeToken) {
        super(position, upgradeToken.entityToken, "#FFFF00");
        this.upgradeToken = upgradeToken;
    }

    public UpgradeToken getUpgradeToken() {
        return upgradeToken;
    }
}
