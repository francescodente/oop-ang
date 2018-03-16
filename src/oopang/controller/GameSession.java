package oopang.controller;

import java.beans.EventHandler;

import oopang.controller.loader.LevelTag;

/**
 * Class including informations about the current session game.
 */
public class GameSession {

    private static final int DEFAULT = 0;

    private int lifes;
    private int score;
    private boolean isMultiplayer;
    private int levelIndex;
    private LevelTag levelType;

    /**
     * Constructor of the class.
     * @param multi
     *      parameter specifying if is a multiplayer session.
     * @param level
     *      the levelTag.
     */
    public GameSession(final boolean multi, final LevelTag level) {
        this.lifes = DEFAULT;
        this.score = DEFAULT;
        this.isMultiplayer = multi;
        this.levelType = level;
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
