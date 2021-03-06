package oopang.view.rendering.gameobject;

import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.Model;
import oopang.model.gameobjects.Wall;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Layers;
import oopang.view.rendering.Sprite;

/**
 * Represents a Renderer for {@link Wall} {@link GameObject}s.
 */
public final class WallRenderer extends GameObjectRenderer<Wall> {

    private static final double MAX_WALL_SIZE = Model.TOTAL_WIDTH;

    /**
     * Creates a new {@link Wall} Renderer given its Wall {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Wall} {@link GameObject}.
     * @param walltexture
     *      The {@link ImageID} of the wall texture to be used.
     */
    public WallRenderer(final Sprite sprite, final Wall gameObject, final ImageID walltexture) {
        super(sprite, gameObject);
        this.setLayer(Layers.WALL_LAYER);
        sprite.setSource(walltexture);
        final double width = gameObject.getWidth() * sprite.getSourceWidth() / MAX_WALL_SIZE;
        final double height = gameObject.getHeight() * sprite.getSourceHeight() / MAX_WALL_SIZE;
        double x = gameObject.getPosition().getX() + Model.TOTAL_WIDTH / 2 - gameObject.getWidth() / 2;
        double y = Model.TOTAL_HEIGHT - (gameObject.getPosition().getY() - Model.WALL_WIDTH + gameObject.getHeight() / 2);
        x *= sprite.getSourceWidth() / MAX_WALL_SIZE;
        y *= sprite.getSourceHeight() / MAX_WALL_SIZE;
        sprite.setSourceWindow(Points2D.of(x, y), Vectors2D.of(width, height));
    }
}
