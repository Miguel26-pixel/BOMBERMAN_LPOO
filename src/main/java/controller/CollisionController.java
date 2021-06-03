package controller;

import controller.collision.CollisionHandler;
import model.arena.Arena;
import controller.collision.Collision;
import model.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollisionController extends GameController{
    public CollisionController(Arena arena) {
        super(arena);
        collisionHandlers = new HashMap<>();
    }

    private final Map<Class, CollisionHandler> collisionHandlers;
    public void handleCollisions(){

        //Get collisions
        List<Collision> collisions = new ArrayList<>();
        List<Entity> entities = arena.getEntities();
        for(int i=0; i<entities.size(); i++){
            for(int j=i +1; j< entities.size(); j++){

                if(entities.get(i).getPosition().equals(entities.get(j).getPosition()))
                    collisions.add(new Collision(entities.get(i), entities.get(j)));
            }
        }

        //Handle collisions
        for(Collision collision: collisions){
            handleCollision(collision);
        }
    }

    public void handleCollision(Collision collision){
        CollisionHandler handler = collisionHandlers.get(collision.getEntity1().getClass());
        if(handler != null)
            handler.handler(collision.getEntity1(), collision.getEntity2());

        handler = collisionHandlers.get(collision.getEntity2().getClass());
        if(handler != null)
            handler.handler(collision.getEntity2(), collision.getEntity1());
    }

    public void addCollisionHandler(Class c, CollisionHandler handler){
        collisionHandlers.put(c, handler);
    }


}
