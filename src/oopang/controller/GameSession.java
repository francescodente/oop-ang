package oopang.controller;

/**
 * Class including informations about the current session game.
 */
public class GameSession {

    private static final int DEFAULT = 0; 
    private int lifes;
    private int score;
    private boolean isMultiplayer;
    private int levelIndex;

    /**
     * Constructor of the class.
     * @param multi
     *      parameter specifying if is a multiplayer session.
     */
    public GameSession(final boolean multi) {
        this.lifes = DEFAULT;
        this.score = DEFAULT;
        this.isMultiplayer = multi;
    }
}
