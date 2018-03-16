package oopang.controller;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import oopang.commons.Command;

import oopang.controller.loader.SessionTag;
import oopang.model.Model;
import oopang.model.input.InputWriter;
import oopang.view.View;


/**
 * This is the concrete implementation of the Controller.
 */
public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private GameLoop gameloop;
    private GameSession gameSession;
    private Map<PlayerTag, InputWriter> input;

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
    public void startGameSession(final SessionTag mode, final boolean isMultiPlayer) {
        this.gameSession = new GameSession(mode, isMultiPlayer);
        //this.gameloop = this.gameSession.getLoop();
        //this.map = this.gameSession.getInputMap();
    }

    @Override
    public void setLevelIndex(final int levelIndex) {
        //gameSession.setLevelIndex
    }

    @Override
    public void pauseGame() {
        this.gameloop.pauseLoop();
    }

    @Override
    public void resume() {
        this.gameloop.resumeLoop();
    }

    @Override
    public void continueGameSession() {
        //this.gameSession.continueGame();
        //this.gameloop = this.gameSession.getLoop();
    }

    @Override
    public void closeGameSession() {
        // TODO reset references
    }

    @Override
    public void sendCommand(final Command cmd, final PlayerTag player) {
        this.gameloop.addCommand(cmd, player);
    }

}
