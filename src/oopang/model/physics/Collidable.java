package oopang.model.physics;

import org.dyn4j.geometry.Convex;

import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;

/**
 * Represents an object that can collide with other collidable objects.
 */
public interface Collidable {
    /**
     * Returns the shape of the object without any transformation.
     * @return
     *      the shape of the object.
     */
    Convex getShape();

    /**
     * Returns the position of the object. During collision detection every point of the 
     * shape will be translated by this amount.
     * @return
     *      the position.
     */
    Point2D getPosition();

    /**
     * Tells this object that it is colliding with another object.
     * @param coll
     *      the {@link Collision} data for this collision.
     */
    void notifyCollision(Collision coll);

    /**
     * Registers an {@link EventHandler} for when the object starts colliding with something.
     * @param handler
     *      the {@link EventHandler}.
     */
    void registerCollisionEvent(EventHandler<Collision> handler);

    /**
     * Unregisters an {@link EventHandler} for when the object starts colliding with something.
     * @param handler
     *      the {@link EventHandler} to remove.
     */
    void unregisterCollisionEvent(EventHandler<Collision> handler);
}
