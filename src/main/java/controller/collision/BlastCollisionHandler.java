package controller.collision;

import controller.BlastController;
import model.entity.Blast;
import model.entity.Entity;
import model.entity.Wall;

public class BlastCollisionHandler implements CollisionHandler{
    private final BlastController blastController;

    public BlastCollisionHandler(BlastController blastController) {
        this.blastController = blastController;
    }

    @Override
    public void handler(Entity a, Entity b) {
        if(b instanceof Blast){
            Entity aux = a;
            a = b;
            b = aux;
        }

        //Do nothing

        if(b instanceof Wall){
            blastController.stopExpanding((Blast) a);
        }
    }
}
