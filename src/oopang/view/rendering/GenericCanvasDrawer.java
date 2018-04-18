package oopang.view.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * A generic canvas drawer that gives basic implementation of the {@link CanvasDrawer} interface.
 */
public abstract class GenericCanvasDrawer implements CanvasDrawer {

    private final List<Renderer> renderers;

    /**
     * Creates a new Canvas drawer with generic data.
     */
    public GenericCanvasDrawer() {
        this.renderers = new ArrayList<>();
    }

    @Override
    public void draw() {
        this.getRenderers().forEach(Renderer::render);
    }

    @Override
    public void addRenderer(final Renderer rend) {
        this.renderers.add(rend);
        Collections.sort(this.renderers);
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
