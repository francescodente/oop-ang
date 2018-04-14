package oopang.view.rendering.powers;

import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * 
 * @author samu
 *
 */
public class ShieldRenderer extends BlinkingPowerRenderer {

    private static final double SHIELD_H_OFFSET = 8;
    private static final double SHIELD_V_OFFSET = 5;
    private static final int SHIELD_LAYER = 4;

    /**
     * Creates a new Shield renderer.
     * @param sprite
     *      the sprite to be used in this renderer
     * @param player
     *      the player who picked up this power
     * @param power
     *      the power activated 
     * @param drawer
     *      the current canvasDrawer
     */
    public ShieldRenderer(final Sprite sprite, final Player player, final TimedPower power, final CanvasDrawer drawer) {
        super(sprite, player, power, drawer);
        sprite.setSource(ImageID.SHIELD);
        sprite.setPivot(Vectors2D.of(0, -1));
        this.setLayer(SHIELD_LAYER);
        sprite.setWidth(player.getWidth() + SHIELD_H_OFFSET);
        sprite.setHeight(player.getHeight() + SHIELD_V_OFFSET);
    }

    @Override
    public void render() {
        this.getSprite().setPosition(this.getPlayer().getPosition());
        super.render();
    }

}
