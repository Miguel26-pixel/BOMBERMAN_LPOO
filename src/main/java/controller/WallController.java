package controller;

import model.arena.Arena;
import model.entity.Upgrade;
import model.entity.Wall;
import model.utils.UpgradeToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class WallController extends GameController{
    public WallController(Arena arena) {
        super(arena);
    }

    public void destroyWall(Wall wall){
        UpgradeToken upgradeToken = hasCoin(wall);
        if(upgradeToken != null){
            Upgrade upgrade = new Upgrade(wall.getPosition(), upgradeToken);
            arena.addEntity(upgrade);
        }
        arena.removeEntity(wall);
    }

    public UpgradeToken hasCoin(Wall wall){
        //25%
        Random random = new Random();
        if(random.nextInt(4) == 1){
            return UpgradeToken.values()[random.nextInt(4)];
        }
        return null;
    }
}
