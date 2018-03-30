package oopang.view.rendering.gameobject;

import oopang.commons.space.Points2D;
import oopang.commons.space.Vectors2D;
import oopang.model.Model;
import oopang.model.gameobjects.Wall;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * Represents a Renderer for {@link Wall} {@link GameObject}s.
 */
public class WallRenderer extends GameObjectRenderer<Wall> {

    private static final double MAX_WALL_SIZE = Model.WORLD_WIDTH + 2 * Model.WALL_WIDTH;
    private static final int WALL_LAYER = 1;
    /**
     * Creates a new {@link Wall} Renderer given its Wall {@link GameObject}.
     * @param sprite
     *      The {@link Sprite} used to render.
     * @param gameObject
     *      The {@link Wall} {@link GameObject}.
     */
    public WallRenderer(final Sprite sprite, final Wall gameObject) {
        super(sprite, gameObject);
        this.setLayer(WALL_LAYER);
        sprite.setSource(ImageID.WALL);
        final double width = gameObject.getWidth() * sprite.getSourceWidth() / MAX_WALL_SIZE;
        final double height = gameObject.getHeight() * sprite.getSourceHeight() / MAX_WALL_SIZE;
        sprite.setSourceWindow(Points2D.ZERO, Vectors2D.of(width, height));
    }
}
