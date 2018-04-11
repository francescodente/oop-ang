package oopang.controller;

import java.util.Optional;

import oopang.commons.Command;
import oopang.commons.events.EventHandler;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.TestLevelLoader;
import oopang.controller.users.FileSystemUserManager;
import oopang.controller.users.User;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.view.GameScene;
import oopang.view.View;


/**
 * This is the concrete implementation of the Controller.
 */
public final class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private GameSession gameSession;
    private User user;
    private FileSystemUserManager userManager;

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
        this.userManager = new FileSystemUserManager();
    }

    @Override
    public void startStoryGameSession(final int levelIndex, final boolean isMultiPlayer) {
        this.gameSession = new StoryModeGameSession(view, model, isMultiPlayer, new TestLevelLoader(), levelIndex);
        this.gameSession.getShouldEndEvent().register(s -> this.handleSessionResult(s));
    }

    @Override
    public void startInifiniteGameSession(final boolean isMultiPlayer) {
        this.gameSession = new InfiniteGameSession(view, model, isMultiPlayer, new TestLevelLoader());
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
    public void closeGameSession() {
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
        this.closeGameSession();
        if (result != LevelResult.LEVEL_COMPLETE) {
            this.view.loadScene(GameScene.GAMEOVER);
        }
    }

    @Override
    public void registerLevelStartedEvent(final EventHandler<LevelData> handler) {
        this.gameSession.getLevelStartedEvent().register(handler);
    }

    @Override
    public boolean registerUser(final String userName, final String password) {
        Optional<User> user = this.userManager.registerUser(userName, password);
        if (user.isPresent()) {
            this.user = user.get();
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUser(final String userName, final String password) {
        Optional<User> user = this.userManager.login(userName, password);
        if (user.isPresent()) {
            this.user = user.get();
            return true;
        }
        return false;
    }

}

