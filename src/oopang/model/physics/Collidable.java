package oopang.model.physics;

import java.util.Optional;

import org.dyn4j.geometry.Convex;

import oopang.commons.events.EventHandler;
import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.model.gameobjects.GameObject;

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
     * Translates the position of this object by the given amount.
     * @param offset
     *      the offset vector.
     */
    void translate(Vector2D offset);

    /**
     * Returns the {@link CollisionTag} for this collidable.
     * @return
     *      the {@link CollisionTag}.
     */
    CollisionTag getCollisionTag();

    /**
     * Returns an optional representing the {@link GameObject} represented by this collidable.
     * @return
     *      the optional {@link GameObject}.
     */
    Optional<GameObject> getAttachedGameObject();

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
