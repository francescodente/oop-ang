package oopang.view.rendering.javafx;

import javafx.scene.canvas.Canvas;
import oopang.controller.DayTime;
import oopang.view.rendering.AbstractRendererFactory;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.Sprite;

/**
 * 
 */
public class JavaFXRendererFactory extends AbstractRendererFactory {

    private final Canvas canvas;

    /**
     * Creates a new factory of renderers for a {@link javafx.scene.canvas.Canvas}.
     * @param gc
     *      the graphics context of the canvas.
     */
    public JavaFXRendererFactory(final Canvas gc) {
        super();
        this.canvas = gc;
    }

    @Override
    public final Sprite createSprite() {
        return new JavaFXImageSprite(canvas.getGraphicsContext2D());
    }

    @Override
    public final Renderer createBackgroundRenderer(final DayTime time, final ImageID id) {
        return new JavaFXBackgroundRenderer(this.createSprite(), time, id);
    }

}
