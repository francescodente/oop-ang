package oopang.view.rendering.powers;

import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.Sprite;

/**
 * 
 */
public class BlinkingPowerRenderer extends TimedPowerRenderer {

    private static final double BLINK_START = 2;
    private static final double BLINK_TIME = 0.2;

    private boolean blink;

    /**
     * Creates a new Blinking Power renderer.
     * @param sprite
     *      the sprite to be used in this renderer
     * @param player
     *      the player who picked up this power
     * @param power
     *      the power activated 
     * @param drawer
     *      the current canvasDrawer
     */
    public BlinkingPowerRenderer(final Sprite sprite, final Player player, final TimedPower power, final CanvasDrawer drawer) {
        super(sprite, player, power, drawer);
        this.blink = true;
        this.getPower().getTimeChangedEvent().register(t -> {
            final double time = power.getRemainingTime();
            if (time <= BLINK_START) {
                blink = Math.round(BLINK_START - time / BLINK_TIME) % 2 != 0;
            } else {
                blink = false;
            }
        });
    }

    @Override
    public void render() {
        if (!blink) {
            super.render();
        }
    }



}
