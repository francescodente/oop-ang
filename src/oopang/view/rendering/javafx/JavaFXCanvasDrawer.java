package oopang.view.rendering.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oopang.view.rendering.GenericCanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.RendererFactory;

/**
 * An object to address draw calls on a JavaFX Canvas object.
 */
public final class JavaFXCanvasDrawer extends GenericCanvasDrawer {

    private final Canvas canvas;
    private final ImageID wallTexture;
    private final JavaFXRendererFactory factory;

    /**
     * Creates a new Canvas drawer that can draw on the given {@link Canvas}.
     * @param canvas
     *      the {@link Canvas} object to render on.
     * @param wallTexture
     *      the texture to be used for walls
     */
    public JavaFXCanvasDrawer(final Canvas canvas, final ImageID wallTexture) {
        super();
        this.canvas = canvas;
        this.wallTexture = wallTexture;
        this.factory = new JavaFXRendererFactory(this, this.wallTexture);
    }

    @Override
    public void draw() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.getRenderers().forEach(r -> {
            gc.save();
            r.render();
            gc.restore();
        });
    }

    @Override
    public RendererFactory getRendererFactory() {
        return this.factory;
    }

    /**
     * Return the canvas object used in this canvasDrawer.
     * @return
     *      the canvas
     */
    public Canvas getCanvas() {
        return this.canvas;
    }
}
