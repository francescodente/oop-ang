package oopang.view.rendering;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * A generic canvas drawer that gives basic implementation of the {@link CanvasDrawer} interface.
 */
public abstract class GenericCanvasDrawer implements CanvasDrawer {

    private final Queue<Renderer> renderers;

    /**
     * Creates a new Canvas drawer with generic.
     */
    public GenericCanvasDrawer() {
        this.renderers = new PriorityQueue<>();
    }

    @Override
    public void draw() {
        this.getRenderers().forEach(Renderer::render);
    }

    @Override
    public void addRenderer(final Renderer rend) {
        this.renderers.add(rend);
    }

    @Override
    public void removeRenderer(final Renderer rend) {
        this.renderers.remove(rend);
    }

    /**
     * Returns an ordered stream of all the renderers that currently need to be rendered.
     * @return
     *      the stream of all renderers.
     */
    protected Stream<Renderer> getRenderers() {
        return this.renderers.stream();
    }
}
