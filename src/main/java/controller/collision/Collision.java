package controller.collision;

import model.entity.*;

public class Collision {

    private Entity entity1, entity2;
    private CollisionType type;
    public Collision(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;

        if(entity2 instanceof Bomber){
            Entity aux = entity1;
            entity1 = entity2;
            entity2 = aux;
        }

        /*
        if(entity1.getClass() == Bomber.class){
            //Bomber collisions
            if(entity2.getClass() == Bomb.class){
                type = CollisionType.BOMBER_BOMB;
            } else if(entity2.getClass() == Blast.class){
                type = CollisionType.BOMBER_BLAST;
            } else if(entity2.getClass() == Monster.class){
                type = CollisionType.BOMBER_MONSTER;
            } else if(entity2.getClass() == Upgrade.class){
                type = CollisionType.BOMBER_UPGRADE;
            }

        } else {
            if(entity2.getClass() == Blast.class){
                Entity aux = entity1;
                entity1 = entity2;
                entity2 = aux;
            }
            //Blast collisions
            if(entity2.getClass() == Monster.class){
                type = CollisionType.BLAST_MONSTER;
            } else if(entity2.getClass() == Upgrade.class){
                type = CollisionType.BLAST_UPGRADE;
            } else if(entity2.getClass() == Wall.class){
                type = CollisionType.BLAST_WALL;
            }
        }

         */
    }

    public Entity getEntity1() {
        return entity1;
    }

    public Entity getEntity2() {
        return entity2;
    }

    @Deprecated
    public CollisionType getCollisionType(){
        return type;
    }
}
