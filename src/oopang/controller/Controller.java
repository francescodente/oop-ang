package oopang.controller;

import oopang.commons.Command;
import oopang.controller.loader.SessionTag;

/**
 * Controller of the application.
 */
public interface Controller {

    /**
     * Creates a new {@link GameSession} object and starts the game.
     * @param mode
     *      an enum to indicate Story or Survival mode.
     * @param isMultiPlayer
     *      true if multiPlayer is active.
     */
    void startGameSession(SessionTag mode, boolean isMultiPlayer);

    /**
     * Set the levelIndex to start with in the GameSession.
     * @param levelIndex
     *      the level index indicating the level to be loaded as first
     */
    void setLevelIndex(int levelIndex);

    /**
     * Pauses the game interrupting the game loop.
     */
    void pauseGame();

    /**
     * Resume the game if it has been paused.
     */
    void resume();

    /**
     * Asks the {@link GameSession} to go on with a new level.
     */
    void continueGameSession();

    /**
     * Closes the current instance of the {@link GameSession}.
     */
    void closeGameSession();

    /**
     * Asks the controller to enqueue a new command to be executed.
     * @param cmd
     *      the command.
     * @param player
     *      the player the command is applied to.
     */
    void sendCommand(Command cmd, PlayerTag player);
}
