package oopang.view.rendering;

/**
 * Represents an object that knows how to draw itself on the canvas.
 */
public interface Renderer extends Comparable<Renderer> {
    /**
     * Draws this object onto the canvas.
     */
    void render();
}
