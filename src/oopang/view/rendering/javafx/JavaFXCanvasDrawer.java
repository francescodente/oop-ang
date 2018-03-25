package oopang.view.rendering.javafx;

import javafx.scene.canvas.Canvas;
import oopang.view.rendering.GenericCanvasDrawer;
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
        this.getRenderers().forEach(r -> {
            this.canvas.getGraphicsContext2D().save();
            r.render();
            this.canvas.getGraphicsContext2D().restore();
        });
    }

    @Override
    public RendererFactory getRendererFactory() {
        return new JavaFXRendererFactory(this.canvas);
    }
}
