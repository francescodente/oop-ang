package oopang.view.rendering.powers;

import oopang.commons.space.Points2D;
import oopang.model.Model;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

public class FrozenFrameRenderer extends TimedPowerRenderer{

    private static final int FRAME_LAYER = Integer.MAX_VALUE;
    private static final double ALPHA_VALUE = 0.85;

    /**
     * Creates a new FrozenFrame renderer.
     * @param sprite
     *      the sprite to be used in this renderer
     * @param player
     *      the player who picked up this power
     * @param power
     *      the power activated 
     * @param drawer
     *      the current canvasDrawer
     */
    public FrozenFrameRenderer(final Sprite sprite, final Player player, final TimedPower power, final CanvasDrawer drawer) {
        super(sprite, player, power, drawer);
        sprite.setSource(ImageID.FROZEN_FRAME);
        sprite.setPosition(Points2D.of(0, Model.WORLD_HEIGHT / 2));
        this.setLayer(FRAME_LAYER);
        sprite.setAlpha(ALPHA_VALUE);
        sprite.setWidth(Model.TOTAL_WIDTH);
        sprite.setHeight(Model.TOTAL_HEIGHT);
    }

}
