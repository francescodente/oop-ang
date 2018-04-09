package oopang.view.rendering.javafx;

import oopang.controller.DayTime;
import oopang.view.rendering.AbstractRendererFactory;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.Sprite;

/**
 * 
 */
public class JavaFXRendererFactory extends AbstractRendererFactory {

    private final JavaFXCanvasDrawer canvasDrawer;

    /**
     * Creates a new factory of renderers for a {@link javafx.scene.canvas.Canvas}.
     * @param canvasdrawer
     *      the graphics context of the canvas.
     * @param walltexture
     *      the wall texture to be used.
     */
    public JavaFXRendererFactory(final JavaFXCanvasDrawer canvasdrawer, final ImageID walltexture) {
        super(walltexture, canvasdrawer);
        this.canvasDrawer = canvasdrawer;
    }

    @Override
    protected final Sprite generateSprite() {
        return new JavaFXImageSprite(this.canvasDrawer.getCanvas().getGraphicsContext2D());
    }

    @Override
    public final Renderer createBackgroundRenderer(final DayTime time, final ImageID id) {
        final Renderer renderer = new JavaFXBackgroundRenderer(this.createSprite(), time, id);
        this.canvasDrawer.addRenderer(renderer);
        return renderer;
    }

}
