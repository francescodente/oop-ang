package oopang.view;
/**
 *  This Class is used by menus to set GameParameters.
 */
public final class GameParameters {

    private static int levelindex;
    private static boolean multiplayer;
    private static boolean isStoryMode;

    private GameParameters() {
    }
    /**
     * Set levelIndex.
     * @param levelindex
     *      The index of level.
     */
    public static void setLevelindex(final int levelindex) {
        GameParameters.levelindex = levelindex;
    }
    /**
     * Set gameMode.
     * @param gameMode
     *      The gameMode.
     */
    public static void setIsStoryMode(final boolean gameMode) {
        GameParameters.isStoryMode = gameMode;
    }
    /**
     * Set the Multiplayer flag mood.
     * @param isMultiplayer
     *      Flag of Multiplayer mood.
     */
    public static void setMultiplayer(final boolean isMultiplayer) {
        GameParameters.multiplayer = isMultiplayer;
    }
    /**
     *  Getter of Level index.
     * @return
     *      Level index.
     */
    public static int getLevelindex() {
        return levelindex;
    }
    /**
     * Getter of Multiplayer.
     * @return
     *      Flag Multiplayer.
     */
    public static boolean isMultiplayer() {
        return multiplayer;
    }
    /**
     *  Getter of GameMode.
     * @return
     *      the gamemode.
     */
    public static boolean getIfStoryMode() {
        return isStoryMode;
    }

}
