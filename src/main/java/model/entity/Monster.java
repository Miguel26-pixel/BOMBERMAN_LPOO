package model.entity;

import model.utils.EntityToken;
import model.utils.Position;

import java.util.Random;

public class Monster extends MovingEntity{
    public Monster(Position position) {
        super(position, EntityToken.MONSTER, "#FF0000");

    }
    public Monster(){
        super(new Position(0, 0), EntityToken.MONSTER, "#FF0000");
    }

    @Override
    public void update() {
        super.update();
    }

}
