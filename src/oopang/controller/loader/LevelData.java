package oopang.controller.loader;

import java.util.Date;

import oopang.controller.DayTime;
import oopang.model.BallColor;
import oopang.model.levels.Level;
import oopang.view.rendering.ImageID;

/**
 * A class containing informations about the level.
 */
public class LevelData {

    private static final Double MORNING = 12.00;
    private static final Double AFTERNOON = 19.00;

    private final BallColor color;
    private final ImageID background;
    private DayTime time;
    private final Date currentTime;
    private final Level level;

    /**
     * Constructor of the class.
     * @param color
     *      The color of the Ball.
     * @param background
     *      The background of the level.
     * @param level
     *      the new level created from loader
     */
    public LevelData(final BallColor color, final ImageID background, final Level level) {
        super();
        this.color = color;
        this.background = background;
        currentTime = new Date();
        findDayTime();
        this.level = level;
    }

    /**
     * Utility method to set the DayTime.
     */
    private void findDayTime() {
        if (currentTime.getTime() < MORNING) {
            time = DayTime.MORNING;
        } else if (currentTime.getTime() > MORNING && currentTime.getTime() < AFTERNOON) {
            time = DayTime.AFTERNOON;
        } else if (currentTime.getTime() > AFTERNOON) {
            time = DayTime.NIGHT;
        }
    }
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
    public ImageID getBackground() {
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

    /**
     * Returns the level created from the loader.
     * @return
     *      the new level
     */
    public Level getLevel() {
        return this.level;
    }

}
