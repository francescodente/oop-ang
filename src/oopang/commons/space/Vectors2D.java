package oopang.commons.space;

/**
 * Utility class for Vector2D objects.
 */
public final class Vectors2D {

    /**
     * Vector with (0,0) values.
     */
    public static final Vector2D ZERO  = Vectors2D.of(0, 0);

    /**
     * Vector representing the up direction. It's value is (0,1).
     */
    public static final Vector2D UP    = Vectors2D.of(0, 1);

    /**
     * Vector representing the down direction. It's value is (0,-1).
     */
    public static final Vector2D DOWN  = Vectors2D.of(0, -1);

    /**
     * Vector representing the right direction. It's value is (1,0).
     */
    public static final Vector2D RIGHT = Vectors2D.of(1, 0);

    /**
     * Vector representing the left direction. It's value is (-1,0).
     */
    public static final Vector2D LEFT  = Vectors2D.of(-1, 0);

    private Vectors2D() {

    }

    /**
     * Create a new Vector2D with the given values.
     * 
     * @param x
     *     x value of the new Vector
     * @param y
     *     y value of the new Vector
     * @return
     *      the new Vector
     */
    public static Vector2D of(final double x, final double y) {
        return new Cartesian(x, y);
    }

    /**
     * Returns the vector going from the first point to the second point.
     * @param from
     *      the first vector.
     * @param to
     *      the second vector.
     * @return
     *      the vector going from the first point to the second point.
     */
    public static Vector2D fromTo(final Point2D from, final Point2D to) {
        return Vectors2D.of(to.getX() - from.getX(), to.getY() - from.getY());
    }

    /**
     * Returns the vector between [LEFT, RIGHT, UP, DOWN] constants whose direction is 
     * closer to the given vector's direction.
     * If the given vector is (0, 0) the constant ZERO is returned.
     * @param vector
     *      The vector object.
     * @return
     *      LEFT, RIGHT, UP, DOWN or ZERO depending on the direction of the vector.
     */
    public static Vector2D getNearestPerpendicularVector(final Vector2D vector) {
        if (vector.equals(ZERO)) {
            return ZERO;
        }
        final double absX = Math.abs(vector.getX());
        final double absY = Math.abs(vector.getY());
        if (absX > absY) {
            if (vector.getX() > 0) {
                return RIGHT;
            } else {
                return LEFT;
            }
        } else {
            if (vector.getY() > 0) {
                return UP;
            } else {
                return DOWN;
            }
        }
    }
}
