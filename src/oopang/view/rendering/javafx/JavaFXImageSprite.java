package oopang.view.rendering.javafx;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import oopang.view.rendering.GenericSprite;
import oopang.view.rendering.ImageID;

/**
 * Represents a sprite that can be renderer on a {@link javafx.scene.canvas.Canvas} object.
 */
public class JavaFXImageSprite extends GenericSprite {

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
    }

    @Override
    public void render() {
        // TODO: take in account pivot.
        this.gc.drawImage(this.image, this.getPosition().getX(), this.getPosition().getY());
    }
}
