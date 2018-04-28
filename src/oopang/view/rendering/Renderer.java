package oopang.view.rendering;

/**
 * Represents an object that knows how to draw itself on the canvas. To decide the order in which
 * renderers will be drawn on the screen, an integer called Layer can be set. Higher layers will be drawn last 
 * (i.e. on top of lower layers).
 */
public interface Renderer extends Comparable<Renderer> {

    /**
     * Draws this object onto the canvas.
     */
    void render();

    /**
     * Sets the layer of this renderer.
     * @param layer
     *      the new layer.
     */
    void setLayer(int layer);

    /**
     * Returns the layer of this renderer.
     * @return
     *      the layer.
     */
    int getLayer();
}
