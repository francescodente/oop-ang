package oopang.controller;

import oopang.commons.Command;
import oopang.commons.PlayerTag;
import oopang.commons.events.EventHandler;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.XMLLevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.model.powers.BasicPowerFactory;
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
        this.gameSession = new StoryModeGameSession(view, model, isMultiPlayer, new XMLLevelLoader(new BasicPowerFactory()), levelIndex);
        this.gameSession.getShouldEndEvent().register(s -> this.handleSessionResult(s));
    }

    @Override
    public void startInifiniteGameSession(final boolean isMultiPlayer) {
        this.gameSession = new InfiniteGameSession(view, model, isMultiPlayer, new XMLLevelLoader(new BasicPowerFactory()));
        this.gameSession.getShouldEndEvent().register(s -> this.handleSessionResult(s));
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
    public void forceCloseGameSession() {
        this.gameSession.handleGameOver(new GameOverStatus(0, LevelResult.FORCE_EXIT));
        this.gameSession = null;
    }

    @Override
    public void sendCommand(final Command cmd, final PlayerTag player) {
        if (this.gameSession.isMultiplayer()) {
            this.gameSession.getGameLoop().addCommand(cmd, player);
        } else if (player == PlayerTag.PLAYER_ONE) {
            this.gameSession.getGameLoop().addCommand(cmd, player);
        }
    }

    private void handleSessionResult(final LevelResult result) {
        this.gameSession = null;
        if (result != LevelResult.LEVEL_COMPLETE && result != LevelResult.FORCE_EXIT) {
            this.view.loadScene(GameScene.GAMEOVER);
        }
    }

    @Override
    public void registerLevelStartedEvent(final EventHandler<LevelData> handler) {
        this.gameSession.getLevelStartedEvent().register(handler);
    }

    @Override
    public int getLifeCount() {
        return this.gameSession.getLifeCount();
    }

}

