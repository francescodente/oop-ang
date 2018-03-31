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

    /**
     * Creates a new Canvas drawer that can draw on the given {@link Canvas}.
     * @param canvas
     *      the {@link Canvas} object to render on.
     */
    public JavaFXCanvasDrawer(final Canvas canvas) {
        super();
        this.canvas = canvas;
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
    public RendererFactory getRendererFactory(final ImageID walltexture) {
        return new JavaFXRendererFactory(this.canvas, walltexture);
    }
}
