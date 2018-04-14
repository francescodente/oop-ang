package oopang.view.rendering.gameobject;


import oopang.commons.PlayerTag;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * Implements the rendering of the Player game object.
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {

    private static final int PLAYER_LAYER = 3;
    private static final double PLAYER_OFFSET = 3;
    /**
     * Constructor of the {@link Player} Renderer.
     * @param sprite
     *      The Sprite {@link Sprite} used to render
     * @param gameObject
     *      The {@link GameObject} in this case a {@link Player}
     * @param canvasDrawer
     *      The {@link CanvasDrawer} used to add sprites.
     */
    public PlayerRenderer(final Sprite sprite, final Player gameObject, final CanvasDrawer canvasDrawer) {
        super(sprite, gameObject);
        this.setLayer(PLAYER_LAYER);
        if (gameObject.getPlayerTag() == PlayerTag.PLAYER_ONE) {
            sprite.setSource(ImageID.PLAYER1);
        } else {
            sprite.setSource(ImageID.PLAYER2);
        }
        sprite.setPivot(Vectors2D.of(0, -1));
        gameObject.getPickupCollectedEvent().register(p -> {
            if (p instanceof TimedPower) {
                canvasDrawer.getRendererFactory().createTimedPowerRenderer((TimedPower) p, gameObject);
            }
        });
    }

    @Override
    public void render() {
        this.getSprite().setPosition(this.getGameObject().getPosition());
        this.getSprite().setWidth(this.getGameObject().getWidth() + PLAYER_OFFSET);
        this.getSprite().setHeight(this.getGameObject().getHeight());
        this.getSprite().render();
    }

}
