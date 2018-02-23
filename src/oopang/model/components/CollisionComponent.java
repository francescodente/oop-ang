package oopang.model.components;

import org.dyn4j.geometry.Convex;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;
import oopang.model.gameobjects.GameObject;
import oopang.model.physics.Collidable;
import oopang.model.physics.Collision;

/**
 * A component that makes the {@link GameObject} it is attached to able to collide with other objects.
 */
public class CollisionComponent extends AbstractComponent implements Collidable {

    private final Convex boundingBox;
    private final Event<Collision> collisionEvent;

    /**
     * Creates a new Collision component with the given bounding box.
     * @param obj
     *      the game object this component is attached to.
     * @param boundingBox
     *      the convex shape of the object.
     */
    public CollisionComponent(final GameObject obj, final Convex boundingBox) {
        super(obj);
        this.boundingBox = boundingBox;
        this.collisionEvent = new Event<>();
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
    public void notifyCollision(final Collision coll) {
        this.collisionEvent.trigger(coll);
    }

    @Override
    public void registerCollisionEvent(final EventHandler<Collision> handler) {
        this.collisionEvent.registerHandler(handler);
    }

    @Override
    public void unregisterCollisionEvent(final EventHandler<Collision> handler) {
        this.collisionEvent.unregisterHandler(handler);
    }
}
