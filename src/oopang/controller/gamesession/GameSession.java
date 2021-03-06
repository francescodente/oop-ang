package oopang.controller.gamesession;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import oopang.commons.events.EventSource;
import oopang.commons.PlayerTag;
import oopang.commons.events.Event;
import oopang.controller.GameLoop;
import oopang.controller.GameLoopThread;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.LevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.model.input.InputController;
import oopang.model.input.InputWriter;
import oopang.model.levels.Level;
import oopang.model.levels.LevelBuilder;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * Class including informations about the current game session.
 */
public abstract class GameSession {

    private GameLoop gameloop;
    private final View scene;
    private final Model world;
    private final LevelLoader loader;
    private final EventSource<LevelData> levelCreatedEvent;
    private final EventSource<LevelResult> shouldEnd;

    private int score;
    private LevelResult lastResult;
    private final boolean multiplayer;

    /**
     * Initializes a new GameSession object.
     * @param view
     *      the {@link View} of the application.
     * @param model
     *      the {@link Model} of the application.
     * @param isMultiPlayer
     *      indicates if the game is single or multi player.
     * @param loader
     *      the object used to create levels.
     */
    public GameSession(final View view, final Model model, final boolean isMultiPlayer, final LevelLoader loader) {
        this.score = 0;
        this.multiplayer = isMultiPlayer;
        this.world = model;
        this.scene = view;
        this.loader = loader;
        this.shouldEnd = new EventSource<>();
        this.levelCreatedEvent = new EventSource<>();
    }

    /**
     * Returns the current {@link GameLoop} thread.
     * @return
     *      the current {@link GameLoop}.
     */
    public final GameLoop getGameLoop() {
        return this.gameloop;
    }

    /**
     * This is the getter of the stage.
     * @return
     *      The current stage.
     */
    public abstract int getStage();

    /**
     * Returns the current score of the session.
     * @return
     *      the current score value.
     */
    public final int getTotalScore() {
        return this.score;
    }

    /**
     * Tells the game session to start the next {@link Level} and create the new {@link GameLoop}.
     */
    public void startNextLevel() {
        final LevelBuilder builder = this.world.getLevelBuilder();
        final Map<PlayerTag, InputWriter> inputMap = new EnumMap<>(PlayerTag.class);
        final InputController input = new InputController();
        inputMap.put(PlayerTag.PLAYER_ONE, input);
        builder.addPlayer(input, PlayerTag.PLAYER_ONE);
        if (this.multiplayer) {
            final InputController inputPlayerTwo = new InputController();
            inputMap.put(PlayerTag.PLAYER_TWO, inputPlayerTwo);
            builder.addPlayer(inputPlayerTwo, PlayerTag.PLAYER_TWO);
        }
        Optional<LevelData> levelData = Optional.empty();
        try {
            levelData = this.getNextLevel(builder);
        } catch (Exception e) {
            this.scene.getDialogFactory().createLevelNotLoaded(e).show();
        }
        if (!levelData.isPresent()) {
            this.shouldEnd.trigger(lastResult);
            return;
        }
        final Level currentLevel = levelData.get().getLevel();
        currentLevel.getGameOverStatusEvent().register(this::handleGameOver);
        this.levelCreatedEvent.trigger(levelData.get());
        this.world.setCurrentLevel(currentLevel);
        this.gameloop = new GameLoopThread(this.scene, this.world, inputMap);
        this.gameloop.start();
    }

    /**
     * Returns true if a new level is available.
     * @return
     *      true if next level is available.
     */
    public abstract boolean hasNextLevel();

    /**
     * Asks subclasses what is the next level to start.
     * @param builder
     *      the builder used to create the level.
     * @return
     *      An optional describing the level data or an empty optional if the game session should end.
     * @throws IOException
     *      when the level is not loaded correctly.
     */
    protected abstract Optional<LevelData> getNextLevel(LevelBuilder builder) throws IOException;

    /**
     * Contains the logic for when the level ends.
     * @param status
     *      the status of the level.
     */
    public void handleGameOver(final GameOverStatus status) {
        this.gameloop.stopLoop();
        this.lastResult = status.getResult();
        if (this.lastResult == LevelResult.FORCE_EXIT) {
            this.scene.loadScene(GameScene.MAIN_MENU);
        } else if (this.hasNextLevel()) {
            switch (this.lastResult) {
            case LEVEL_COMPLETE:
                this.scene.loadScene(GameScene.LEVEL_STEP);
                break;
            case PLAYER_DEAD:
            case OUT_OF_TIME:
                this.scene.loadScene(GameScene.LEVEL_RESET);
                break;
            default:
                break;
            }
        } else {
            this.scene.loadScene(GameScene.GAMEOVER);
            this.shouldEnd.trigger(lastResult);
        }
    }

    /**
     * Add the given score to the global score.
     * @param score
     *      the level score.
     */
    protected void addScore(final int score) {
        this.score += score;
    }

    /**
     * Returns the {@link LevelLoader} reference for the concrete children.
     * @return
     *      the level loader
     */
    protected LevelLoader getLoader() {
        return this.loader;
    }

    /**
     * Returns the event which triggers when the GameSession should end.
     * @return
     *      the should end event.
     */
    public Event<LevelResult> getShouldEndEvent() {
        return this.shouldEnd;
    }

    /**
     * Returns the event which triggers when a new level is created.
     * @return
     *      the level created event.
     */
    public Event<LevelData> getLevelStartedEvent() {
        return this.levelCreatedEvent;
    }

    /**
     * Wether the session is multiplayer or not.
     * @return
     *      true if the session is multiplayer
     */
    public boolean isMultiplayer() {
        return this.multiplayer;
    }

    /**
     * Returns the life count.
     * @return
     *      the number of lives
     */
    public int getLifeCount() {
        return 0;
    }
}
