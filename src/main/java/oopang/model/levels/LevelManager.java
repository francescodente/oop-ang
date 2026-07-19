package oopang.model.levels;

/**
 * An helper class for level managing and loading.
 */
public final class LevelManager {
    private static Level current;

    private LevelManager() {
    }

    /**
     * Sets the current {@link Level} instance.
     * @param level
     *      the new level to be set.
     */
    public static void setCurrentLevel(final Level level) {
        current = level;
    }

    /**
     * Returns the current {@link Level} instance.
     * @return
     *      the current level.
     */
    public static Level getCurrentLevel() {
        return current;
    }
}
