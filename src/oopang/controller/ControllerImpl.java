package oopang.controller;

import oopang.commons.Command;
import oopang.model.Model;
import oopang.view.View;


/**
 * This is the concrete implementation of the Controller.
 */
public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private GameSession gameSession;

    /**
     * Create a new Controller instance.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void startStoryGameSession(final int levelIndex, final boolean isMultiPlayer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void startInifiniteGameSession(final boolean isMultiPlayer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pauseGame() {
        this.gameSession.getGameLoop().pauseLoop();
    }

    @Override
    public void resume() {
        this.gameSession.getGameLoop().resumeLoop();
    }

    @Override
    public void continueGameSession() {
        this.gameSession.continueGame();
    }

    @Override
    public void closeGameSession() {
        // TODO reset references
    }

    @Override
    public void sendCommand(final Command cmd, final PlayerTag player) {
        this.gameSession.getGameLoop().addCommand(cmd, player);
    }
}
