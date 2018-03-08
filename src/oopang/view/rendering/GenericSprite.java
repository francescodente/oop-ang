package oopang.view.rendering;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;

/**
 * Provides a generic implementation of the {@link Sprite} interface that stores transformation
 * values (pivot and position). Subclasses should use these informations when overriding the
 * render() method.
 */
public abstract class GenericSprite extends GenericRenderer implements Sprite {

    private Point2D position;
    private Vector2D pivot;

    @Override
    public final void setPosition(final Point2D pos) {
        this.position = pos;
    }

    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    @Override
    public final void setPivot(final Vector2D pivot) {
        this.pivot = pivot;
    }

    @Override
    public final Vector2D getPivot() {
        return this.pivot;
    }
}
