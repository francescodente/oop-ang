package oopang.controller;

import oopang.commons.Command;
import oopang.commons.events.EventHandler;
import oopang.controller.loader.LevelData;
import oopang.model.Model;
import oopang.view.GameScene;
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
        this.gameSession = new StoryModeGameSession(view, model, isMultiPlayer, levelIndex);
        this.gameSession.registerShouldEndEvent(s -> this.handleSessionResult(s));
    }

    @Override
    public void startInifiniteGameSession(final boolean isMultiPlayer) {
        this.gameSession = new InfiniteGameSession(view, model, isMultiPlayer);
        this.gameSession.registerShouldEndEvent(s -> this.handleSessionResult(s));
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
        this.gameSession.startNextLevel();
    }

    @Override
    public void closeGameSession() {
        this.gameSession = null;
        this.view.loadScene(GameScene.GAMEOVER);
    }

    @Override
    public void sendCommand(final Command cmd, final PlayerTag player) {
        this.gameSession.getGameLoop().addCommand(cmd, player);
    }

    private void handleSessionResult(final Boolean shouldEnd) {
        if (shouldEnd) {
            this.closeGameSession();
        }
    }

    @Override
    public void registerLevelStartedEvent(final EventHandler<LevelData> handler) {
        this.gameSession.registerLevelStartedEvent(handler);
    }

}

