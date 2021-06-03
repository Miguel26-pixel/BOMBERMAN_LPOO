package controller;

import model.arena.Arena;
import model.entity.Blast;
import model.entity.Bomb;
import model.utils.DirectionToken;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class BombController extends GameController{

    private final BlastController blastController;
    public BombController(Arena arena, BlastController blastController) {
        super(arena);
        this.blastController = blastController;

    }

    public void updateBombs(){
        List<Bomb> explodedBombs = new ArrayList<>();
        List<Bomb> bombs = arena.getBombs();
        for(Bomb bomb : bombs){
            if(bomb.canExplode()){
                explodedBombs.add(bomb);
            }
        }

        for(Bomb bomb: explodedBombs){
            explodeBomb(bomb);
            bomb.getCreator().addBomb();
        }



    }

    public void explodeBomb(Bomb bomb){
        Position current = bomb.getPosition();
        Blast blast = new Blast(current, bomb.getLevel() + 1, DirectionToken.LEFT);
        arena.addBlast(blast);
        blastController.expandBlast(blast);
        blast.setBlastDirection(DirectionToken.DOWN);
        blastController.expandBlast(blast);
        blast.setBlastDirection(DirectionToken.RIGHT);
        blastController.expandBlast(blast);
        blast.setBlastDirection(DirectionToken.UP);
        blastController.expandBlast(blast);
        arena.removeEntity(bomb);



        //Expand left
        /*if(current.getX() > 0 && arena.isEmpty(current.getLeft())){
            Blast newBlast = new Blast(current.getLeft(), bomb.getLevel(), DirectionToken.LEFT);
            arena.addBlast(newBlast);
        }
        //Expand right
        if(current.getX() < arena.getWidth() - 1 && arena.isEmpty(current.getRight())){
            Blast newBlast = new Blast(current.getRight(), bomb.getLevel(), DirectionToken.RIGHT);
            arena.addBlast(newBlast);
        }

        //Expand top
        if(current.getY() > 0 && arena.isEmpty(current.getUp())){
            Blast newBlast = new Blast(current.getUp(), bomb.getLevel(), DirectionToken.UP);
            arena.addBlast(newBlast);
        }

        //Expand bottom
        if(current.getY() < arena.getHeight() - 1 && arena.isEmpty(current.getDown())){
            Blast newBlast = new Blast(current.getUp(), bomb.getLevel(), DirectionToken.DOWN);
            arena.addBlast(newBlast);
        }
        */


    }
}
