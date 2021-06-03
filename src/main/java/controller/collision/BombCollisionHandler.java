package controller.collision;

import controller.BombController;
import model.entity.*;

public class BombCollisionHandler implements CollisionHandler{
    private final BombController bombController;

    public BombCollisionHandler(BombController bombController) {
        this.bombController = bombController;
    }

    @Override
    public void handler(Entity a, Entity b) {
        if(b instanceof Bomb){
            Entity aux = a;
            a = b;
            b = aux;
        }

        if(b instanceof Bomber){
            //Kick bomb?
        } else if(b instanceof Blast){
            bombController.explodeBomb((Bomb) a);
        }


    }
}
