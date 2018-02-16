package oopang.view.rendering;

/**
 * Provides a generic implementation of the {@link Renderer} interface that stores the layer.
 */
public abstract class GenericRenderer implements Renderer {

    private int renderLayer;

    @Override
    public final int compareTo(final Renderer other) {
        return Integer.compare(this.getLayer(), other.getLayer());
    }

    @Override
    public void setLayer(final int layer) {
        this.renderLayer = layer;
    }

    @Override
    public int getLayer() {
        return this.renderLayer;
    }
}
