package controller;

import gui.GUI;
import model.arena.Arena;
import model.entity.Entity;
import model.entity.Monster;
import model.entity.Wall;
import model.player.Player;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterController extends GameController{
    final private static int INITIALCOUNTER = 10;
    private int counter;

    public MonsterController(Arena arena){
        super(arena);
        counter = INITIALCOUNTER;
    }
    public void moveMonsterLeft(int nMonster) {
        Position position = arena.getMonster(nMonster).getPosition();
        if(position.getX() > 0)
            moveMonster(position.getLeft(), nMonster);
    }

    public void moveMonsterRight(int nMonster) {
        Position position = arena.getMonster(nMonster).getPosition();
        if(position.getX() < arena.getWidth() - 1)
            moveMonster(position.getRight(), nMonster);
    }

    public void moveMonsterUp(int nMonster) {
        Position position = arena.getMonster(nMonster).getPosition();
        if(position.getY() > 0)
            moveMonster(position.getUp(), nMonster);
    }

    public void moveMonsterDown(int nMonster) {
        Position position = arena.getMonster(nMonster).getPosition();
        if(position.getY() < arena.getHeight() - 1)
            moveMonster(position.getDown(), nMonster);
    }

    private void moveMonster(Position position, int nMonster) {
        if (arena.isEmpty(position)) {
            arena.getMonster(nMonster).setPosition(position);
        }
    }

    public void moveMonsters() {
        counter--;
        if(counter == 0) {
            for(Monster monster : arena.getMonsters()){
                List<Position> possiblePositions = new ArrayList<>();
                List<Position> allPositions = new ArrayList<>();
                Position position = monster.getPosition();
                allPositions.add(position.getUp());
                allPositions.add(position.getRight());
                allPositions.add(position.getLeft());
                allPositions.add(position.getDown());

                for(Position p : allPositions){
                    if(!p.isOutOfBounds(arena.getWidth(), arena.getHeight())){
                        Entity entity = arena.getEntity(p);
                        if(!(entity instanceof Wall)){
                            possiblePositions.add(p);
                        }
                    }
                }
                if(possiblePositions.isEmpty()){
                    possiblePositions.add(position);
                }
                monster.setPosition(possiblePositions.get(new Random().nextInt(possiblePositions.size())));

            }
            counter = INITIALCOUNTER;
        }
    }

    public void destroyMonster(Monster monster){
        arena.removeEntity(monster);
    }
}
