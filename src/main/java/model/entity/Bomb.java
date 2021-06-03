package model.entity;

import model.utils.EntityToken;
import model.utils.Position;

public class Bomb extends MovingEntity{
    private int ticksLeft, nextColorChange;
    private int level;
    private Bomber creator;
    private String originalColor;

    public Bomb(Position position, int fuse, Bomber creator) {
        super(position, EntityToken.BOMB, "#FF0000");
        originalColor = "#FF0000";
        ticksLeft = fuse * 20;
        nextColorChange = ticksLeft / 20;
        this.level = creator.getBlastLevel();
        this.creator = creator;
    }
    public Bomb(int fuse, int level) {
        super(new Position(0, 0), EntityToken.BOMB, "#FF0000");
        originalColor = "#FF0000";
        this.ticksLeft = fuse * 20;
        this.level = level;

    }

    @Override
    public void update() {
        super.update();
        ticksLeft--;
        nextColorChange--;
        if(nextColorChange == 0){
            changeBombColor();
            nextColorChange = (ticksLeft + 20) / 20;
        }

    }

    private void changeBombColor(){
        if(color.equals(this.originalColor)){
            color = "#000000";
        } else color = this.originalColor;
    }

    public boolean canExplode(){
        return ticksLeft == 0;
    }
    @Override
    public char getCharacter() {
        return EntityToken.BOMB.label;
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
        this.originalColor = color;
    }

    public int getLevel() {
        return level;
    }

    public void setTicksLeft(int ticksLeft) {
        this.ticksLeft = ticksLeft;
    }

    public Bomber getCreator() {
        return creator;
    }
}
