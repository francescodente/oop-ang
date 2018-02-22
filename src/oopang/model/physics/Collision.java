package oopang.model.physics;

import oopang.commons.space.Vector2D;

/**
 * Stores data relative to a collision event.
 */
public class Collision {

    private Vector2D normal;
    private Collidable other;

    /**
     * Creates a new collision instance.
     * @param other
     *      the other collidable object.
     * @param normal
     *      the vector perpendicular to the perimeter of the other object and pointing towards
     *      the first one.
     */
    public Collision(final Collidable other, final Vector2D normal) {
        super();
        this.normal = normal;
        this.other = other;
    }

    /**
     * Returns the vector perpendicular to the perimeter of the other other object and pointing
     * towards the first object.
     * @return
     *      the normal vector.
     */
    public Vector2D getNormal() {
        return this.normal;
    }

    /**
     * Returns the {@link Collidable} that is colliding with the first one.
     * @return
     *      the other {@link Collidable}.
     */
    public Collidable getOther() {
        return this.other;
    }
}
