package oopang.model.components;

import org.dyn4j.geometry.Convex;

import oopang.commons.events.*;
import oopang.commons.space.*;
import oopang.model.gameobjects.GameObject;
import oopang.model.physics.Collidable;
import oopang.model.physics.Collision;

public class CollisionComponent extends AbstractComponent implements Collidable {

    private Convex boundingBox;
    private Event<Collision> collisionEvent;
    
    public CollisionComponent(GameObject obj, Convex boundingBox) {
        super(obj);
        this.boundingBox = boundingBox;
        collisionEvent = new Event<>();
    }
    
    @Override
    public void start() {
        //CollisionManager.addCollidable(this);
    }
    
    @Override
    public Convex getShape() {
        return this.boundingBox;
    }

    @Override
    public Point2D getPosition() {
       return this.getGameObject().getPosition();
    }

    @Override
    public void notifyCollision(Collision coll) {
        collisionEvent.trigger(coll);
    }

    @Override
    public void registerCollisionEvent(EventHandler<Collision> handler) {
        collisionEvent.registerHandler(handler);
        
    }

    @Override
    public void unregisterCollisionEvent(EventHandler<Collision> handler) {
        collisionEvent.unregisterHandler(handler);
    }

}
