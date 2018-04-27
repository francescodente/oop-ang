package oopang.view.rendering;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;

/**
 * Provides a generic implementation of the {@link Sprite} interface that stores transformation
 * values (pivot and position). Subclasses should use these informations when overriding the
 * render() method.
 */
public abstract class GenericSprite extends GenericRenderer implements Sprite {
    private static final double DEF_DIM = 100;
    private static final double MAX_ALPHA = 1;
    private static final double MIN_ALPHA = 0;

    private Point2D srcTopLeft;
    private Vector2D srcOffset;
    private Point2D position;
    private Vector2D pivot;
    private double width;
    private double height;
    private double alpha;

    /**
     * Initializes base data for the sprite.
     */
    public GenericSprite() {
        super();
        this.srcTopLeft = Points2D.ZERO;
        this.srcOffset = Vectors2D.ZERO;
        this.pivot = Vectors2D.ZERO;
        this.position = Points2D.ZERO;
        this.width = DEF_DIM;
        this.height = DEF_DIM;
        this.alpha = MAX_ALPHA;
    }

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

    @Override
    public final void setWidth(final double width) {
        this.width = width;
    }

    @Override
    public final void setHeight(final double height) {
        this.height = height;
    }

    @Override
    public final void setSourceWindow(final Point2D topLeft, final Vector2D offset) {
        this.srcTopLeft = topLeft;
        this.srcOffset = offset;
    }

    /**
     * Returns the position of the top-left corner of the source rectangle.
     * @return
     *      the top-left corner.
     */
    protected final Point2D getSourceTopLeftCorner() {
        return this.srcTopLeft;
    }

    /**
     * Returns the offset of the bottom-right corner from the top-left corner.
     * @return
     *      the offset in pixels
     */
    protected final Vector2D getSourceOffset() {
        return this.srcOffset;
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final double getHeight() {
        return this.height;
    }

    /**
     * Sets the source to the selected ImageId.
     */
    @Override
    public void setSource(final ImageID sourceID) {
        this.setSourceWindow(Points2D.ZERO, Vectors2D.of(this.getSourceWidth(), this.getSourceHeight()));
    }

    @Override
    public final void setAlpha(final double alpha) {
        this.alpha = Math.min(MAX_ALPHA, Math.max(MIN_ALPHA, alpha));
    }

    @Override
    public final double getAlpha() {
        return this.alpha;
    }
}
