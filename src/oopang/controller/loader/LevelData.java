package oopang.controller.loader;

import java.util.Calendar;
import java.util.Date;

import oopang.controller.DayTime;
import oopang.model.BallColor;
import oopang.model.levels.Level;
import oopang.view.rendering.ImageID;

/**
 * A class containing informations about the level.
 */
public class LevelData {

    private static final int MORNING = 12;
    private static final int AFTERNOON = 19;

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
        this.time = findDayTime(Calendar.getInstance());
        this.level = level;
    }

    /**
     * Utility method to set the DayTime.
     */
    private DayTime findDayTime(final Calendar rightNow) {
        if (rightNow.get(Calendar.HOUR_OF_DAY) < MORNING) {
            return DayTime.MORNING;
        } else if (rightNow.get(Calendar.HOUR_OF_DAY) > MORNING && rightNow.get(Calendar.HOUR_OF_DAY) < AFTERNOON) {
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
