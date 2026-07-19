package oopang.controller.loader;

import java.util.Calendar;

import oopang.controller.DayTime;
import oopang.model.levels.Level;
import oopang.view.rendering.ImageID;

/**
 * A wrapper class containing informations about the level that has been loaded.
 */
public class LevelData {

    private static final int MORNING = 8;
    private static final int AFTERNOON = 14;
    private static final int NIGHT = 20;

    private final ImageID background;
    private final ImageID walltexture;
    private final DayTime time;
    private final Level level;

    /**
     * Constructor of the class.
     * @param background
     *      The background of the level.
     *@param walltexture
     *      The wall texture of the level.
     * @param level
     *      the new level created from loader
     */
    public LevelData(final ImageID background, final ImageID walltexture, final Level level) {
        super();
        this.background = background;
        this.walltexture = walltexture;
        this.time = findDayTime(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        this.level = level;
    }

    /**
     * Utility method to set the DayTime.
     */
    private DayTime findDayTime(final int hour) {
        if (hour >= MORNING && hour < AFTERNOON) {
            return DayTime.MORNING;
        } else if (hour >= AFTERNOON && hour < NIGHT) {
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
     * Getter of the wall texture.
     * @return
     *      The wall texture of the level.
     */
    public ImageID getWallTexture() {
        return this.walltexture;
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
