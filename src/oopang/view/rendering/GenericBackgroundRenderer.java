package oopang.view.rendering;

/**
 * This is a Generic renderer for backgrounds.
 */
public class GenericBackgroundRenderer extends GenericRenderer {

    private final Sprite image;

    /**
     * Create a new GenericBackgroundRenderer.
     * @param sprite
     *      the sprite used to render this background.
     */
    public GenericBackgroundRenderer(final Sprite sprite) {
        super();
        this.image = sprite;
    }

    @Override
    public void render() {
        this.image.render();
    }

    /**
     * Give access to the sprite from children.
     * @return
     *      the sprite related to this renderer.
     */
    protected Sprite getSprite() {
        return this.image;
    }

}
