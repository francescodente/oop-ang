package oopang.controller.loader;

import oopang.controller.DayTime;
import oopang.model.BallColor;
import oopang.view.Background;

/**
 * A class containing informations about the level.
 */
public class LevelData {

    private BallColor color;
    private Background background;
    private DayTime time;

    /**
     * Getter of the color.
     * @return
     *      The color of the balls.
     */
    public BallColor getColor() {
        return this.color;
    }

    /**
     * Getter of the background.
     * @return
     *      The background of the level.
     */
    public Background getBackground() {
        return this.background;
    }

    /**
     * Getter of the time.
     * @return
     *      The time.
     */
    public DayTime getTime() {
        return this.time;
    }

}
