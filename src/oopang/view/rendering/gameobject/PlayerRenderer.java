package oopang.view.rendering.gameobject;

import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.Player;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * Implements the rendering of the Player game object.
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {

    private static final int PLAYER_LAYER = 1;
    /**
     * Constructor of the {@link Player} Renderer.
     * @param sprite
     *      The Sprite {@link Sprite} used to render
     * @param gameObject
     *      The {@link GameObject} in this case a {@link Player}
     */
    public PlayerRenderer(final Sprite sprite, final Player gameObject) {
        super(sprite, gameObject);
        this.setLayer(PLAYER_LAYER);
        sprite.setSource(ImageID.PLAYER);
        sprite.setPivot(Vectors2D.of(0, -1));
    }

}
