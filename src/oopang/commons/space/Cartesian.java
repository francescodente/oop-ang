package oopang.commons.space;

/**
 * Represents a pair of (x,y) coordinates that can act as a Vector or 
 * a point in 2D space.
 */
public class Cartesian implements Vector2D, Point2D {

    private static final double EPSILON = 0.001;

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
        if (this.getModule() == 0) {
            return Vectors2D.ZERO;
        }
        return this.multiply(1 / this.getModule());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Vector2D) && !(obj instanceof Point2D)) {
            return false;
        }
        final double otherX = obj instanceof Vector2D
                ? ((Vector2D) obj).getX()
                : ((Point2D) obj).getX();
        final double otherY = obj instanceof Vector2D
                ? ((Vector2D) obj).getY()
                : ((Point2D) obj).getY();
        if (otherX < this.x - EPSILON || otherX > this.x + EPSILON) {
            return false;
        } else if (otherY < this.y - EPSILON || otherY > this.y + EPSILON) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
