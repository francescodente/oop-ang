package oopang.view.rendering;

import oopang.controller.DayTime;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;

/**
 * A factory for objects that can be rendered by a specific {@link CanvasDrawer}.
 * Use {@link CanvasDrawer}.getRendererFactory() to get the correct instance.
 */
public interface RendererFactory {
    /**
     * Creates a new Renderer that is responsible for drawing the given GameObject.
     * The type of renderer may be different depending on the type of GameObject.
     * @param obj
     *      the GameObject.
     * @return
     *      the Renderer for the given GameObject.
     */
    Renderer createGameObjectRenderer(GameObject obj);

    /**
     * Creates a new Sprite object.
     * @return
     *      the Sprite object.
     */
    Sprite createSprite();

    /**
     * Creates a new {@link TimedPower} renderer.
     * @param power
     *      the power to be rendered
     * @param player
     *      the player who picked up the power
     * @return
     *      a new {@link TimedPower} renderer
     */
    Renderer createTimedPowerRenderer(TimedPower power, Player player);

    /**
     * Creates a new renderer for the background of the game.
     * @param time
     *      the day time of the background.
     * @param id
     *      the image id for the background.
     * @return
     *      the renderer.
     */
    Renderer createBackgroundRenderer(DayTime time, ImageID id);
}
