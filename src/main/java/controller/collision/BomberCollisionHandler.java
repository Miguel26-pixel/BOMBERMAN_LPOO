package controller.collision;

import controller.BomberController;
import model.entity.*;

public class BomberCollisionHandler implements CollisionHandler{
    private final BomberController bomberController;

    public BomberCollisionHandler(BomberController bomberController) {
        this.bomberController = bomberController;
    }

    @Override
    public void handler(Entity a, Entity b) {
        if(b instanceof Bomber){
            Entity aux = a;
            a = b;
            b = aux;
        }

        if(b instanceof Monster){
            bomberController.decreaseHealth((Bomber) a);
        } else if(b instanceof Blast){
            bomberController.decreaseHealth((Bomber) a);
        } else if(b instanceof Wall){
            bomberController.goBack((Bomber) a);
        } else if(b instanceof Bomb){
            bomberController.goBack((Bomber) a);
        } else if(b instanceof Bomber){
            bomberController.goBack((Bomber) a);
        } else if(b instanceof  Upgrade){
            ((Bomber) a).upgrade(((Upgrade)b).getUpgradeToken());
            bomberController.getArena().removeEntity(b);
        }
    }
}
