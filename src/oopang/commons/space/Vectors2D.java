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
}
