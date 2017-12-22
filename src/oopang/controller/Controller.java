package oopang.controller;

import oopang.commons.Command;

/**
 * Controller of the application.
 */
public interface Controller {

    /**
     * Loads the specified level and starts the game loop.
     * @param levelIndex
     *      the index of the level to be loaded.
     */
    void startGame(int levelIndex);

    /**
     * Pauses the game interrupting the game loop.
     */
    void pauseGame();

    /**
     * Closes the current instance of the level.
     */
    void closeGame();

    /**
     * Asks the controller to enqueue a new command to be executed.
     * @param cmd
     *      the command.
     */
    void sendCommand(Command cmd);
}
