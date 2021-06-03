package controller.collision;

import controller.MonsterController;
import model.entity.*;

public class MonsterCollisionHandler implements CollisionHandler{
    private final MonsterController monsterController;

    public MonsterCollisionHandler(MonsterController monsterController) {
        this.monsterController = monsterController;
    }


    @Override
    public void handler(Entity a, Entity b) {
        if(b instanceof Monster){
            Entity aux = a;
            a = b;
            b = aux;
        }
        if(b instanceof Bomber){
            monsterController.destroyMonster((Monster) a);
        } else if(b instanceof Blast){
            monsterController.destroyMonster((Monster) a);
        } else if(b instanceof Bomb){
            a.setPosition(((Monster)a).getPreviousPosition());
        }
    }
}
