package oopang.commons;

/**
 * Utility class for Point2D objects.
 */
public final class Points2D {

    /**
     * Point representing the (0,0) coordinates.
     */
    public static final Point2D ZERO = Points2D.of(0, 0);

    private Points2D() {

    }

    /**
     * Create a new Point2D with the given values.
     * 
     * @param x
     *     x value of the new Point
     * @param y
     *     y value of the new Point
     * @return
     *     the new Point
     */
    public static Point2D of(final double x, final double y) {
        return new Cartesian(x, y);
    }
}
