package model.entity;

import model.utils.DirectionToken;
import model.utils.EntityToken;
import model.utils.Position;
import model.utils.UpgradeToken;

public class Bomber extends MovingEntity{
    private int health,  ticks, nextMove;
    private int bombLevel, blastLevel, speedLevel;
    private int bombsLeft;
    private boolean gotHit;
    private String originalColor;
    static char[] characters = {'"', '%', '\'', '&'};
    public Bomber(Position position) {
        super(position, EntityToken.BOMBER, "#FF0000");
        bombLevel = 0;
        bombsLeft = 1;
        blastLevel = 0;
        speedLevel = 0;
        direction = DirectionToken.DOWN;
        health = 3;
        ticks = 20;
        nextMove = 10 - speedLevel;
        gotHit=false;
    }
    public Bomber(){
        super(new Position(0, 0), EntityToken.BOMBER, "#FF0000");
        bombsLeft = 1;
        bombLevel = 0;
        blastLevel = 0;
        speedLevel = 0;
        direction = DirectionToken.DOWN;
        health = 3;
        ticks = 20;
        nextMove = 10 - speedLevel;
        gotHit=false;
    }

    @Override
    public void update() {
        super.update();
        if(gotHit && ticks>0){
            ticks--;
            changeBomberColor();
        } else if(color == "#000000"){
            changeBomberColor();
        }
        if(ticks==0){
            gotHit=false;
            ticks=20;
        }
        if(nextMove > 0) {
            nextMove--;
        }
    }

    public boolean canMove(){
        return nextMove == 0;
    }

    public void resetNextMove(){
        nextMove = 10 - speedLevel;
    }

    private void changeBomberColor(){
        if(color.equals(this.originalColor)){
            color = "#000000";
        } else color = this.originalColor;
    }

    public void upgrade(UpgradeToken upgradeToken){
        switch (upgradeToken) {
            case SPEED_UPGRADE : {
                if(speedLevel<10) {
                    speedLevel++;
                }
                break;
            }
            case BOMB_UPGRADE : {
                if(bombLevel<10) {
                    bombLevel++;
                    bombsLeft++;
                }
                break;
            }
            case BLAST_UPGRADE : {
                if(blastLevel<10) {
                    blastLevel++;
                }
                break;
            }
            case HEALTH_UPGRADE : {
                if(health<3){
                    health++;
                }
                break;
            }
        }
    }
    @Override
    public char getCharacter() {
        return characters[direction.value];
    }

    @Override
    public String getColor() {
        return color;
    }

    public int getBombLevel() {
        return bombLevel;
    }

    public int getBlastLevel() {
        return blastLevel;
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

@Deprecated
    public void decreaseHealth(){

	health--;
	gotHit = true;
}

    public int getHealth(){return health;}

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGotHit(boolean gotHit) {
        this.gotHit = gotHit;
    }

    public void addBomb(){
        bombsLeft++;
    }
    public void removeBomb(){
        bombsLeft--;
    }

    public boolean canPlaceBomb(){
        return bombsLeft > 0;
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
        this.originalColor = color;
    }
}
