package oopang.controller;

import java.util.Optional;
import java.util.function.Consumer;

import oopang.commons.Command;
import oopang.commons.PlayerTag;
import oopang.commons.events.EventHandler;
import oopang.controller.leaderboard.FileSystemLeaderboardManager;
import oopang.controller.leaderboard.Leaderboard;
import oopang.controller.leaderboard.LeaderboardManager;
import oopang.controller.leaderboard.LeaderboardRecord;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.XMLLevelLoader;
import oopang.model.GameOverStatus;
import oopang.controller.users.FileSystemUserManager;
import oopang.controller.users.User;
import oopang.controller.users.UserManager;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.model.powers.BasicPowerFactory;
import oopang.view.View;

/**
 * This is the concrete implementation of the Controller.
 */
public final class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private GameSession gameSession;
    private User user;
    private final UserManager userManager;
    private final LeaderboardManager leaderboardManager;
    private Leaderboard leaderboard;
    private Consumer<Leaderboard> saveAction;

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
        this.leaderboardManager = new FileSystemLeaderboardManager();
    }

    @Override
    public void startStoryGameSession(final int levelIndex, final boolean isMultiPlayer) {
        this.gameSession = new StoryModeGameSession(view, model, isMultiPlayer, new XMLLevelLoader(new BasicPowerFactory()), levelIndex);
        this.gameSession.getShouldEndEvent().register(s -> this.handleSessionResult(s));
        this.leaderboard = this.leaderboardManager.loadStoryModeLeaderboard().get();
        this.saveAction = l -> this.leaderboardManager.saveStoryModeLeaderboard(l);
    }

    @Override
    public void startInifiniteGameSession(final boolean isMultiPlayer) {
        this.gameSession = new InfiniteGameSession(view, model, isMultiPlayer, new XMLLevelLoader(new BasicPowerFactory()));
        this.gameSession.getShouldEndEvent().register(s -> this.handleSessionResult(s));
        this.leaderboard = this.leaderboardManager.loadSurvivalModeLeaderboard().get();
        this.saveAction = l -> this.leaderboardManager.saveSurvivalModeLeaderboard(l);
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
        if (result == LevelResult.PLAYER_DEAD || result == LevelResult.OUT_OF_TIME) {
            this.leaderboard.addRecord(new LeaderboardRecord(user.getName(), this.gameSession.getTotalScore(), 1));
            this.gameSession = null;
            this.saveAction.accept(this.leaderboard);
        } else if (result == LevelResult.FORCE_EXIT) {
            this.gameSession = null;
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

    @Override
    public boolean registerUser(final String userName, final String password) {
        final Optional<User> user = this.userManager.registerUser(userName, password);
        if (user.isPresent()) {
            this.user = user.get();
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUser(final String userName, final String password) {
        final Optional<User> user = this.userManager.login(userName, password);
        if (user.isPresent()) {
            this.user = user.get();
            return true;
        }
        return false;
    }

    @Override
    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    @Override
    public boolean isUserLoaded() {
        return this.user != null;
    }

}

