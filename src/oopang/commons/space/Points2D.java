package oopang.commons.space;

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

    /**
     * Returns the distance between point a and b.
     * @param a
     *      the first point.
     * @param b
     *      the second point.
     * @return
     *      the distance.
     */
    public static double distance(final Point2D a, final Point2D b) {
        return Vectors2D.fromTo(a, b).getModule();
    }

    /**
     * Linearly interpolates between point a and b by t.
     * @param a
     *      the first point.
     * @param b
     *      the second point.
     * @param t
     *      a value between 0 and 1 that indicates the percentage of the interpolation.
     *      If t = 0, the result is a.
     *      If t = 1, the result is b.
     *      If 0 < t < 1, the result is a point between a and b.
     * @return
     *      the resulting point.
     */
    public static Point2D lerp(final Point2D a, final Point2D b, final double t) {
        return a.translate(Vectors2D.fromTo(a, b).multiply(t));
    }
}
