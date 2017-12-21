package oopang.controller;

import oopang.commons.Command;

public interface Controller {
    
    /**
     * 
     * @param LevelIndex
     */
    void startGame(final int LevelIndex);
    
    /**
     * 
     */
    void pauseGame();
    
    /**
     * 
     */
    void closeGame();
    
    /**
     * 
     * @param cmd
     */
    void sendCommand(Command cmd);
}
