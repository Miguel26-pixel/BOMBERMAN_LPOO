package controller.collision;

import model.entity.Entity;

public interface CollisionHandler<T extends Entity> {
    public void handler(T a, Entity b);
}
