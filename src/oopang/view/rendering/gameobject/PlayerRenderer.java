package oopang.view.rendering.gameobject;

import java.util.Optional;

import oopang.commons.PlayerTag;
import oopang.commons.Timeable;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.Player;
import oopang.model.powers.PowerTag;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * Implements the rendering of the Player game object.
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {

    private static final int PLAYER_LAYER = 3;
    private static final double PLAYER_OFFSET = 3;
    private static final double SHIELD_H_OFFSET = 8;
    private static final double SHIELD_V_OFFSET = 5;
    private Optional<Sprite> shield;
    private int activeShields;
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
        shield = Optional.empty();
        this.activeShields = 0;
        this.setLayer(PLAYER_LAYER);
        if (gameObject.getPlayerTag() == PlayerTag.PLAYER_ONE) {
            sprite.setSource(ImageID.PLAYER1);
        } else {
            sprite.setSource(ImageID.PLAYER2);
        }
        sprite.setPivot(Vectors2D.of(0, -1));
        gameObject.getPickupCollectedEvent().register(p -> {
            if (p.getPowertag() == PowerTag.TIMEDSHIELD) {
                this.activeShields++;
                if (!this.shield.isPresent()) {
                    final Sprite shieldsprite = canvasDrawer.getRendererFactory().createSprite();
                    shieldsprite.setSource(ImageID.SHIELD);
                    shieldsprite.setPivot(Vectors2D.of(0, -1));
                    shieldsprite.setLayer(PLAYER_LAYER + 1);
                    shieldsprite.setWidth(this.getGameObject().getWidth() + SHIELD_H_OFFSET);
                    shieldsprite.setHeight(this.getGameObject().getHeight() + SHIELD_V_OFFSET);
                    this.shield = Optional.of(shieldsprite);
                }
                final Timeable time = (Timeable) p;
                time.getTimeOutEvent().register(n -> {
                    this.activeShields--;
                    this.shield.ifPresent(s -> {
                        if (this.activeShields == 0) {
                            canvasDrawer.removeRenderer(s);
                            this.shield = Optional.empty();
                        }
                    });
                }); 
            }
        });
    }

    @Override
    public void render() {
        this.shield.ifPresent(s -> s.setPosition(this.getGameObject().getPosition()));
        this.getSprite().setPosition(this.getGameObject().getPosition());
        this.getSprite().setWidth(this.getGameObject().getWidth() + PLAYER_OFFSET);
        this.getSprite().setHeight(this.getGameObject().getHeight());
        this.getSprite().render();
    }

}
