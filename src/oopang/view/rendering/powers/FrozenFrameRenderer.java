package oopang.view.rendering.powers;

import oopang.commons.space.Points2D;
import oopang.model.Model;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Sprite;

/**
 * This class is a renderer that displays a frozen frame around the canvas when the freeze power
 * is activated.
 */
public class FrozenFrameRenderer extends TimedPowerRenderer {

    private static final int FRAME_LAYER = Integer.MAX_VALUE;
    private static final double FADE_TIME = 1;
    private static final double ALPHA_MAX = 0.85;
    private static final double EPSILON = 0.01;
    private static final double FADE_RATE = 0.02;

    private double timeout;

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
        sprite.setAlpha(0);
        power.getTimeChangedEvent().register(t -> {
            final double time = power.getRemainingTime();
            if (t >= 1 - EPSILON) {
                timeout = time;
            }
            if (time > timeout - FADE_TIME) {
                sprite.setAlpha(Math.min(sprite.getAlpha() + FADE_RATE, ALPHA_MAX));
            } else if (time < FADE_TIME) {
                sprite.setAlpha(Math.min(sprite.getAlpha() - FADE_RATE, ALPHA_MAX));
            }

        });
        sprite.setWidth(Model.TOTAL_WIDTH);
        sprite.setHeight(Model.TOTAL_HEIGHT);
    }


}
