package oopang.commons;

import oopang.commons.space.Point2D;

/**
 * This interface is implemented by object that has a position and can be positioned.
 */
public interface Positionable {

    /**
     * Returns the current position of this GameObject.
     * @return
     *      current position.
     */
    Point2D getPosition();

    /**
     * Sets the current position of this GameObject to the given Point2D.
     * @param position
     *      the new position.
     */
    void setPosition(Point2D position);
}
