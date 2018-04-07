package oopang.view.rendering;

/**
 * Represents an object able to draw the specified Renderer objects onto a canvas.
 */
public interface CanvasDrawer {
    /**
     * Draws the current Renderer objects onto the canvas.
     */
    void draw();

    /**
     * Adds a new object to be rendered on the canvas.
     * @param rend
     *      the object to be rendered.
     */
    void addRenderer(Renderer rend);

    /**
     * Tells the canvas to stop rendering an object.
     * @param rend
     *      the renderer to remove.
     */
    void removeRenderer(Renderer rend);

    /**
     * Returns the factory that should be used to generate compatible renderers for this canvas drawer.
     * @param walltexture
     *      the wall texture to be used.
     * @return
     *      the factory object.
     */
    RendererFactory getRendererFactory();
}
