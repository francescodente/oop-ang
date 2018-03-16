package oopang.controller;

import oopang.controller.loader.SessionTag;


/**
 * Class including informations about the current session game.
 */
public class GameSession {

    private static final int DEFAULT = 0;

    private int lifes;
    private int score;
    private boolean isMultiplayer;
    private int levelIndex;
    private SessionTag levelType;

    /**
     * Constructor of the class.
     * @param multi
     *      parameter specifying if is a multiplayer session.
     * @param tag
     *      parameter specifying the game mode to be chose.
     */
    public GameSession(final SessionTag tag, final boolean multi) {
        this.lifes = DEFAULT;
        this.score = DEFAULT;
        this.isMultiplayer = multi;
        this.levelType = tag;
    }

    /**
     * Setter of LevelIndex.
     * @param index
     *      Index of the level to load.
     */
    public void setLevelIndex(final int index) {
        this.levelIndex = index;
    }

    /**
     * Getter of the index level.
     * @return
     *      The level index.
     */
    public int getLevelIndex() {
        return levelIndex;
    }

    private void handleGameResult() {
    }
}
