package oopang.view.rendering.javafx;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import oopang.view.rendering.GenericSprite;
import oopang.view.rendering.ImageID;

/**
 * Represents a sprite that can be renderer on a {@link javafx.scene.canvas.Canvas} object.
 */
public final class JavaFXImageSprite extends GenericSprite {

    private Image image;
    private final GraphicsContext gc;

    /**
     * Creates a new JavaFX sprite with the given GraphicsContext.
     * @param gc
     *      the GraphicsContext.
     */
    public JavaFXImageSprite(final GraphicsContext gc) {
        super();
        this.gc = gc;
    }

    @Override
    public void setSource(final ImageID sourceID) {
        this.image = ImageManager.getManager().getImage(sourceID);
        super.setSource(sourceID);
    }

    @Override
    public void render() {
        this.gc.translate(
                this.getPosition().getX(),
                this.getPosition().getY());
        this.gc.scale(1, -1);
        this.gc.setGlobalAlpha(this.getAlpha());
        this.gc.drawImage(this.image,
                this.getSourceTopLeftCorner().getX(),
                this.getSourceTopLeftCorner().getY(),
                this.getSourceOffset().getX(),
                this.getSourceOffset().getY(),
                (this.getPivot().getX() - 1) * this.getWidth() / 2,
                (this.getPivot().getY() - 1) * this.getHeight() / 2,
                this.getWidth(),
                this.getHeight());
    }

    @Override
    public double getSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public double getSourceHeight() {
        return this.image.getHeight();
    }
}
