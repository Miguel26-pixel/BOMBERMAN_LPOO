package model.entity;

import model.utils.EntityToken;
import model.utils.Position;

public class Wall extends StaticEntity{
    public Wall(Position position) {
        super(position, EntityToken.WALL, "#eeeeee" );
    }
}
