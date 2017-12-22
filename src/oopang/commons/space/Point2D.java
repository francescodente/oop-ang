package oopang.commons.space;

/**
 * Represents a point in 2D space.
 */
public interface Point2D {

    /**
     * Translates this Point by the given Vector.
     * @param vector
     *      the Vector that represent the translation.
     * @return
     *      new Point with its new value.
     */
    Point2D translate(Vector2D vector);

    /**
     * Sets X to the given value.
     * @param value
     *      the value to be set as new X.
     * @return
     *      new Point with the new Y value.
     */
    Point2D setPointX(double value);

    /**
     * Sets Y to the given value.
     * @param value
     *      the value to be set as new Y.
     * @return
     *      new Point with the new Y value.
     */
    Point2D setPointY(double value);

    /**
     * Returns the X value of the Vector.
     * @return
     *      X value.
     */
    double getX();

    /**
     * Returns the Y value of the Vector.
     * @return
     *      Y value.
     */
    double getY();
}
