package oopang.view.rendering.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oopang.view.rendering.GenericCanvasDrawer;
import oopang.view.rendering.RendererFactory;

/**
 * An object to address draw calls on a JavaFX Canvas object.
 */
public class JavaFXCanvasDrawer extends GenericCanvasDrawer {

    private final GraphicsContext gc;

    /**
     * Creates a new Canvas drawer that can draw on the given {@link Canvas}.
     * @param canvas
     *      the {@link Canvas} object to render on.
     */
    public JavaFXCanvasDrawer(final Canvas canvas) {
        super();
        this.gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void draw() {
        this.getRenderers().forEach(r -> {
            this.gc.save();
            r.render();
            this.gc.restore();
        });
    }

    @Override
    public RendererFactory getRendererFactory() {
        return new JavaFXRendererFactory(this.gc);
    }
}
