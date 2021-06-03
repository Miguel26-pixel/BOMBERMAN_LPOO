package controller.collision;

import controller.WallController;
import model.entity.Blast;
import model.entity.Entity;
import model.entity.Wall;

public class WallCollisionHandler implements  CollisionHandler{
    private final WallController wallController;

    public WallCollisionHandler(WallController wallController) {
        this.wallController = wallController;
    }

    @Override
    public void handler(Entity a, Entity b) {
        if(b instanceof Wall){
            Entity aux = a;
            a = b;
            b = aux;
        }

        if(b instanceof Blast){
            wallController.destroyWall((Wall) a);
        }
    }
}
