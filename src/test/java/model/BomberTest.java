package model;

import model.entity.Bomber;
import model.utils.UpgradeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BomberTest {

    @Test
    public void testUpgrades(){
        Bomber bomber = new Bomber();
        Assertions.assertEquals(0, bomber.getBombLevel());
        bomber.upgrade(UpgradeToken.BLAST_UPGRADE);
        Assertions.assertEquals(1, bomber.getBlastLevel());
        bomber.upgrade(UpgradeToken.BLAST_UPGRADE);
        Assertions.assertEquals(2, bomber.getBlastLevel());
        bomber.upgrade(UpgradeToken.BOMB_UPGRADE);
        Assertions.assertEquals(1, bomber.getBombLevel());
        bomber.upgrade(UpgradeToken.SPEED_UPGRADE);
        bomber.upgrade(UpgradeToken.SPEED_UPGRADE);
        bomber.upgrade(UpgradeToken.SPEED_UPGRADE);
        Assertions.assertEquals(3, bomber.getSpeedLevel());

    }

    @Test
    public void testHealth(){
        Bomber bomber = new Bomber();
        int initialHealth = bomber.getHealth();
        initialHealth--;
        bomber.decreaseHealth();
        Assertions.assertEquals(initialHealth, bomber.getHealth());
    }
}
