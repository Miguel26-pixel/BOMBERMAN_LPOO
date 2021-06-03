package model.entity;

import model.utils.DirectionToken;
import model.utils.EntityToken;
import model.utils.Position;

public abstract class MovingEntity extends GenericEntity{
    protected Position previousPosition;
    protected DirectionToken direction;

    public MovingEntity(Position position, EntityToken entityToken, String color) {
        super(position, entityToken, color);
        previousPosition = position;
    }

    @Override
    public void update() {
        super.update();
    }


    @Override
    public void setPosition(Position position) {
        previousPosition = this.position;
        super.setPosition(position);

    }

    public boolean setDirection(DirectionToken direction){
        if(direction != this.direction) {
            this.direction = direction;
            return true;
        }
        return false;

    }

    public DirectionToken getDirection(){
        return direction;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }
}
