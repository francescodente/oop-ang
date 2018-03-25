package oopang.controller;

import oopang.commons.Command;
import oopang.commons.events.EventHandler;
import oopang.model.gameobjects.GameObject;

/**
 * Controller of the application.
 */
public interface Controller {

    /**
     * Starts a new story game session from a given level.
     * @param levelIndex
     *      the index of the starting level.
     * @param isMultiPlayer
     *      true if multiPlayer is active.
     */
    void startStoryGameSession(int levelIndex, boolean isMultiPlayer);

    /**
     * Starts a new infinite mode game session.
     * @param isMultiPlayer
     *      true if multiPlayer is active.
     */
    void startInifiniteGameSession(boolean isMultiPlayer);

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

    /**
     * Registers a new {@link EventHandler} to the object created event for the
     * current level.
     * @param handler
     *      the {@link EventHandler} object.
     */
    void registerObjectCreatedEvent(EventHandler<GameObject> handler);
}
