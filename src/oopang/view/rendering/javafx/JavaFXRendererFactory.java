package oopang.view.rendering.javafx;

import javafx.scene.canvas.GraphicsContext;
import oopang.view.rendering.AbstractRendererFactory;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.Sprite;

/**
 * 
 */
public class JavaFXRendererFactory extends AbstractRendererFactory {

    private final GraphicsContext gc;

    /**
     * Creates a new factory of renderers for a {@link javafx.scene.canvas.Canvas}.
     * @param gc
     *      the graphics context of the canvas.
     */
    public JavaFXRendererFactory(final GraphicsContext gc) {
        super();
        this.gc = gc;
    }

    @Override
    public Sprite createSprite() {
        return new JavaFXImageSprite(gc);
    }

    @Override
    public Renderer createBackgroundRenderer() {
        return null;
    }

}
