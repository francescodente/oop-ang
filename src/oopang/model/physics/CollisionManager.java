package oopang.model.physics;

/**
 * Represents an object that is responsible of detecting collisions between
 * {@link Collidable} objects.
 */
public interface CollisionManager {
    /**
     * Checks the currently registered collidables for collision and notifies the ones
     * that are actually colliding. If any intersecting pair of objects is overlapping,
     * this method takes care of separating them before the collision event is triggered.
     */
    void step();

    /**
     * Adds a new {@link Collidable} to be checked for collision.
     * @param coll
     *      the {@link Collidable} object.
     */
    void addCollidable(Collidable coll);

    /**
     * Removes the given {@link Collidable} from the collision detection manager.
     * @param coll
     *      the {@link Collidable} to be removed.
     */
    void removeCollidable(Collidable coll);
}
