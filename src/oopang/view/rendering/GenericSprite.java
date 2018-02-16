package oopang.view.rendering;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;

/**
 * Provides a generic implementation of the {@link Sprite} interface that stores transformation
 * values (pivot, position and rotation). Subclasses should use these informations when overriding the
 * render() method.
 */
public abstract class GenericSprite extends GenericRenderer implements Sprite {

    private Point2D position;
    private Vector2D pivot;
    private double angle;

    @Override
    public void setPosition(final Point2D pos) {
        this.position = pos;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPivot(final Vector2D pivot) {
        this.pivot = pivot;
    }

    @Override
    public Vector2D getPivot() {
        return this.pivot;
    }

    @Override
    public void setAngle(final double angle) {
        this.angle = angle;
    }

    @Override
    public double getAngle() {
        return this.angle;
    }
}
