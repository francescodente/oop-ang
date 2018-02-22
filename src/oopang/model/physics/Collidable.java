package oopang.model.physics;

import org.dyn4j.geometry.Convex;

import oopang.commons.space.Point2D;

/**
 * Represents an object that can coĺlide with other collidable objects.
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
}
