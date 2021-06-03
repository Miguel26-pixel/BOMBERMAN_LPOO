package model.entity;

import model.utils.EntityToken;
import model.utils.Position;

public abstract class StaticEntity extends GenericEntity{
    public StaticEntity(Position position, EntityToken character, String color) {
        super(position, character, color);
    }


}
