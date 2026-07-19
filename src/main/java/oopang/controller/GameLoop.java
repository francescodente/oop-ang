package oopang.controller;

import oopang.commons.Command;
import oopang.commons.PlayerTag;

/**
 * Represents an engine that periodically updates the world and renders it on the view.
 */
public interface GameLoop {

    /**
     * Enables the game loop.
     */
    void start();

    /**
     * Temporarily stops the loop until resume() is called.
     */
    void pauseLoop();

    /**
     * Makes the loop restart if it was previously paused.
     */
    void resumeLoop();

    /**
     * Stops the loop and the running thread.
     */
    void stopLoop();

    /**
     * Try to add a new Command to the commandQueue.
     * @param cmd
     *      the new command to be added
     * @param player 
     *      the player tag
     * @return 
     *      true if the command is successful added
     */
    boolean addCommand(Command cmd, PlayerTag player);
}
