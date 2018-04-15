package oopang.view.rendering.powers;

import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.GenericRenderer;
import oopang.view.rendering.Sprite;

/**
 * 
 *
 */
public abstract class TimedPowerRenderer extends GenericRenderer {

    private final Sprite sprite;
    private final Player player;
    private final TimedPower power;
    private final CanvasDrawer canvasDrawer;

    /**
    * Creates a new TimedPower renderer.
    * @param sprite
    *      the sprite to be used in this renderer
    * @param player
    *      the player who picked up this power
    * @param power
    *      the power activated 
    * @param drawer
    *      the current canvasDrawer
    */
    public TimedPowerRenderer(final Sprite sprite, final Player player, final TimedPower power, final CanvasDrawer drawer) {
        super();
        this.sprite = sprite;
        this.player = player;
        this.power = power;
        this.canvasDrawer = drawer;
        this.power.getTimeOutEvent().register(n -> this.canvasDrawer.removeRenderer(this));
    }

    @Override
    public void render() {
        this.sprite.render();
    }

    /**
     * Getter for the sprite for children.
     * @return
     *      the sprite.
     */
    protected Sprite getSprite() {
        return sprite;
    }

    /**
     * Getter for the player object for children.
     * @return
     *      the player.
     */
    protected Player getPlayer() {
        return player;
    }

    /**
     * Getter for the power object for children.
     * @return
     *      the power.
     */
    protected TimedPower getPower() {
        return power;
    }

}
