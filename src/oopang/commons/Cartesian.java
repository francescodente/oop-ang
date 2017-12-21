package oopang.commons;

public class Cartesian implements Vector2D, Point2D {
    
    private final double x;
    private final double y;
    
    public Cartesian(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point2D translate(Vector2D vector) {
        return Points2D.of(this.x + vector.getX(), this.y + vector.getY());
    }

    @Override
    public Point2D setPointX(double value) {
        return Points2D.of(value, this.y);
    }

    @Override
    public Point2D setPointY(double value) {
        return Points2D.of(this.x, value);
    }

    @Override
    public Vector2D sumVector(Vector2D vector) {
        return Vectors2D.of(this.x + vector.getX(), this.y + vector.getY());
    }

    @Override
    public Vector2D multiply(double value) {
        return Vectors2D.of(this.x * value, this.y * value);
    }

    @Override
    public Vector2D setVectorX(double value) {
        return Vectors2D.of(value, this.y);
    }

    @Override
    public Vector2D setVectorY(double value) {
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
        final double m = this.getModule();
        return Vectors2D.of(this.x / m, this.y / m);
    }
}
