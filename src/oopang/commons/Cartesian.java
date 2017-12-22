package oopang.commons;

/**
 * Represent a pair of (x,y) coordinates that can represent a Vector or 
 * a point in 2D space.
 */
public class Cartesian implements Vector2D, Point2D {

    private final double x;
    private final double y;

    /**
     * Creates a new Cartesian object with (x,y) coordinates. 
     * @param x
     *      X coordinate
     * @param y
     *      Y coordinate
     */
    public Cartesian(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point2D translate(final Vector2D vector) {
        return Points2D.of(this.x + vector.getX(), this.y + vector.getY());
    }

    @Override
    public Point2D setPointX(final double value) {
        return Points2D.of(value, this.y);
    }

    @Override
    public Point2D setPointY(final double value) {
        return Points2D.of(this.x, value);
    }

    @Override
    public Vector2D sumVector(final Vector2D vector) {
        return Vectors2D.of(this.x + vector.getX(), this.y + vector.getY());
    }

    @Override
    public Vector2D multiply(final double value) {
        return Vectors2D.of(this.x * value, this.y * value);
    }

    @Override
    public Vector2D setVectorX(final double value) {
        return Vectors2D.of(value, this.y);
    }

    @Override
    public Vector2D setVectorY(final double value) {
        return Vectors2D.of(this.x, value);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getModule() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public Vector2D normalized() {
        return this.multiply(1 / this.getModule());
    }
}
