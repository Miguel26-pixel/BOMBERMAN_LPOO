package controller;

import model.arena.Arena;
import model.entity.*;

import model.utils.DirectionToken;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class BlastController extends GameController{

    private List<Entity> destroyedEntities;

    public BlastController(Arena arena) {
        super(arena);

    }

    public void updateBlasts(){

        List<Blast> blasts = arena.getBlasts();
        List<Blast> removeBlasts = new ArrayList<>();
        for(int i=0; i < blasts.size(); i++){
            Blast blast = blasts.get(i);
            if(blast.canExpand()){
                expandBlast(blast);
            }else if(blast.canDisappear()){
                removeBlasts.add(blast);
            }
        }

        for(Blast blast : removeBlasts){
            arena.removeBlast(blast);
        }
    }
    public void expandBlast(Blast blast){
        Blast newBlast = new Blast(blast.getPosition().getNext(blast.getBlastDirection()),
                          blast.getBlastsLeft() - 1,
                                   blast.getBlastDirection());
        blast.setDirection(DirectionToken.NONE);
        Entity entity = arena.getEntity(newBlast.getPosition());
        arena.addBlast(newBlast);
        /*
        if(entity == null) {
            arena.addBlast(newBlast);
        }
        else if(entity.getClass() == Wall.class){
            destroyedEntities.add(entity);
            destroyedEntities.add(entity);
        } else if(entity.getClass() == Monster.class){
			destroyedEntities.add(entity);
		} else if(entity.getClass() == Bomb.class){
            destroyedEntities.add(entity);
        }
         */

    }

    public void stopExpanding(Blast blast){
        blast.setBlastsLeft(0);
    }

}
