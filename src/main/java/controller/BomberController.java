package controller;

import gui.GUI;
import model.arena.Arena;
import model.entity.Bomber;
import model.entity.Entity;
import model.entity.MovingEntity;
import model.entity.Upgrade;
import model.utils.DirectionToken;
import model.utils.Position;

public class  BomberController extends GameController{
    private final EffectController effectController;
    public BomberController(Arena arena, EffectController effectController){
        super(arena);
        this.effectController = effectController;
    }
    public void moveBomberLeft(Bomber bomber) {
        Position position = bomber.getPosition();
        if(!bomber.setDirection(DirectionToken.LEFT)) {
            if (position.getX() > 0)
                moveBomber(position.getNext(DirectionToken.LEFT), bomber);
        }
    }

    public void moveBomberRight(Bomber bomber) {
        Position position = bomber.getPosition();
        if(!bomber.setDirection(DirectionToken.RIGHT)) {
            if (position.getX() < arena.getWidth() - 1)
                moveBomber(position.getNext(DirectionToken.RIGHT), bomber);
        }
    }

    public void moveBomberUp(Bomber bomber) {
        Position position = bomber.getPosition();
        if(!bomber.setDirection(DirectionToken.UP)) {
            if (position.getY() > 0)
                moveBomber(position.getNext(DirectionToken.UP), bomber);
        }
    }

    public void moveBomberDown(Bomber bomber) {
        Position position = bomber.getPosition();
        if(!bomber.setDirection(DirectionToken.DOWN)) {
            if (position.getY() < arena.getHeight() - 1)
                moveBomber(position.getNext(DirectionToken.DOWN), bomber);
        }
    }

    private void moveBomber(Position position, Bomber bomber) {
        if(bomber.canMove()) {
            Entity entity = arena.getEntity(position);

            if (!(entity instanceof Bomber))
                bomber.setPosition(position);
            bomber.resetNextMove();
        }
    }

    public void goBack(Bomber bomber){
        bomber.setPosition(bomber.getPreviousPosition());
    }

    public void placeBomb(Bomber bomber){
        Position bombPosition = bomber.getPosition().getNext(bomber.getDirection());
        if(!bombPosition.isOutOfBounds(arena.getWidth(), arena.getHeight())) {
            if (arena.isEmpty(bombPosition) && bomber.canPlaceBomb()) {
                bomber.removeBomb();
                arena.addBomb(bombPosition, 5, bomber);

            }
        }
    }

    public void doAction(Bomber bomber, GUI.ACTION action) {
        if (action == GUI.ACTION.UP) moveBomberUp(bomber);
        else if (action == GUI.ACTION.RIGHT) moveBomberRight(bomber);
        else if (action == GUI.ACTION.DOWN) moveBomberDown(bomber);
        else if (action == GUI.ACTION.LEFT) moveBomberLeft(bomber);
        else if (action == GUI.ACTION.PLACEBOMB) placeBomb(bomber);
    }

    public void decreaseHealth(Bomber bomber){
        if(!effectController.isInvulnerable(bomber)) {
            int health = bomber.getHealth();
            if (health == 0) {
                arena.removeEntity(bomber);
            } else {
                bomber.setHealth(--health);
                bomber.setGotHit(true);
                effectController.addInvulnerability(2, bomber);
            }
        }
    }
}
