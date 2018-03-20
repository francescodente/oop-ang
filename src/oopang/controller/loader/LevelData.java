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

    private final ImageID background;
    private final DayTime time;
    private final Level level;

    /**
     * Constructor of the class.
     * @param background
     *      The background of the level.
     * @param level
     *      the new level created from loader
     */
    public LevelData(final ImageID background, final Level level) {
        super();
        this.background = background;
        this.time = findDayTime(new Date());
        this.level = level;
    }

    /**
     * Utility method to set the DayTime.
     */
    private DayTime findDayTime(final Date currentTime) {
        if (currentTime.getTime() < MORNING) {
            return DayTime.MORNING;
        } else if (currentTime.getTime() > MORNING && currentTime.getTime() < AFTERNOON) {
            return DayTime.AFTERNOON;
        }
        return DayTime.NIGHT;
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
