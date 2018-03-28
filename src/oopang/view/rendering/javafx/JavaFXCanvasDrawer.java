package oopang.view.rendering.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oopang.model.levels.BaseLevel;
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
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.save();
        final double canvasWidth = this.canvas.getWidth();
        final double canvasHeight = this.canvas.getHeight();
        gc.scale(1, -1);
        gc.translate(canvasWidth / 2, -canvasHeight);
        gc.scale(canvasWidth / BaseLevel.WORLD_WIDTH, canvasHeight / BaseLevel.WORLD_HEIGHT);
        this.getRenderers().forEach(r -> {
            gc.save();
            r.render();
            gc.restore();
        });
        gc.restore();
    }

    @Override
    public RendererFactory getRendererFactory() {
        return new JavaFXRendererFactory(this.canvas);
    }
}
