package model.entity;

import model.utils.DirectionToken;
import model.utils.EntityToken;
import model.utils.Position;

public class Blast extends MovingEntity{
    private DirectionToken blastDirection;
    private int ticksTillNextBlast;
    private int blastsLeft;
    private int ticksTillDisappear;

    public Blast(Position position, int blastLevel, DirectionToken blastDirection) {
        super(position, EntityToken.BLAST, "#FFA000");
        this.blastDirection = blastDirection;
        this.blastsLeft = blastLevel;
        ticksTillNextBlast = 3;
        ticksTillDisappear = 20;
    }

    @Override
    public void update() {
        super.update();
        ticksTillNextBlast--;
        ticksTillDisappear--;
    }

    public boolean canExpand(){
        return ticksTillNextBlast == 0 && blastsLeft > 0 && direction != DirectionToken.NONE;
    }



    public boolean canDisappear(){
        return ticksTillDisappear <= 0;
    }

    public DirectionToken getBlastDirection() {
        return blastDirection;
    }

    public void setBlastDirection(DirectionToken blastDirection) {
        this.blastDirection = blastDirection;
    }

    public int getBlastsLeft() {
        return blastsLeft;
    }

    public void setBlastsLeft(int blastsLeft) {
        this.blastsLeft = blastsLeft;
    }
}
