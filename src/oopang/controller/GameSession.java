package oopang.controller;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.LevelLoader;
import oopang.controller.loader.TestLevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.Model;
import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputController;
import oopang.model.input.InputWriter;
import oopang.model.levels.Level;
import oopang.model.levels.SinglePlayerLevel;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * Class including informations about the current session game.
 */
public abstract class GameSession {

    private GameLoop gameloop;
    private Level currentLevel;
    private final View scene;
    private final Model world;
    private final LevelLoader loader;
    private final Event<Boolean> shouldEnd;

    private int score;
    private final boolean isMultiPlayer;

    /**
     * Initializes a new GameSession object.
     * @param view
     *      the {@link View} of the application.
     * @param model
     *      the {@link Model} of the application.
     * @param isMultiPlayer
     *      indicates if the game is single or multi player.
     */
    public GameSession(final View view, final Model model, final boolean isMultiPlayer) {
        this.score = 0;
        this.isMultiPlayer = isMultiPlayer;
        this.world = model;
        this.scene = view;
        this.loader = new TestLevelLoader();
        this.shouldEnd = new Event<>();
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
     * Returns the current score of the session.
     * @return
     *      the current score value.
     */
    public final int getTotalScore() {
        return this.score;
    }

    /**
     * Tells the game session to start the next level and create the new game loop.
     */
    public void startNextLevel() {
        Optional<LevelData> levelData = Optional.empty();
        try {
            levelData = this.getNextLevel();
        } catch (Exception e) {
            this.scene.getDialogFactory().createLevelNotLoaded(e).show();
        }
        if (!levelData.isPresent()) {
            this.shouldEnd.trigger(true);
            return;
        }
        this.currentLevel = levelData.get().getLevel();
        final Map<PlayerTag, InputWriter> inputMap = new EnumMap<>(PlayerTag.class);
        final InputController input = new InputController();
        inputMap.put(PlayerTag.PLAYER_ONE, input);
        this.currentLevel = new SinglePlayerLevel(this.currentLevel, input);
        if (this.isMultiPlayer) {
            final InputController inputPlayerTwo = new InputController();
            inputMap.put(PlayerTag.PLAYER_TWO, inputPlayerTwo);
            this.currentLevel = new SinglePlayerLevel(this.currentLevel, inputPlayerTwo);
        }
        this.currentLevel.registerGameOverEvent(this::handleGameOver);
        this.gameloop = new GameLoop(this.scene, this.world, inputMap);
        this.scene.loadScene(GameScene.GAME_GUI);
        this.world.setCurrentLevel(this.currentLevel);
    }

    /**
     * Asks subclasses what is the next level to start.
     * @return
     *      An optional describing the level data or an empty optional if the game session should end.
     * @throws Exception
     *      when the level is not loaded correctly.
     */
    protected abstract Optional<LevelData> getNextLevel() throws Exception;

    /**
     * Contains the logic for when the level ends.
     * @param status
     *      the status of the level.
     */
    protected abstract void handleGameOver(GameOverStatus status);

    /**
     * Add the given score to the global score.
     * @param score
     *      the level score.
     */
    protected void addScore(final int score) {
        this.score += score;
    }

    /**
     * Returns the level loader reference for the concrete children.
     * @return
     *      the level loader
     */
    protected LevelLoader getLoader() {
        return this.loader;
    }

    /**
     * Register the status of the GameSession.
     * @param handler
     *      The handler of GameSession to register
     */
    public void registerShouldEndEvent(final EventHandler<Boolean> handler) {
        this.shouldEnd.registerHandler(handler);
    }

    /**
     * Registers a new {@link EventHandler} to the object created event for the
     * current level.
     * @param handler
     *      the {@link EventHandler} object.
     */
    public void registerObjectCreatedEvent(final EventHandler<GameObject> handler) {
        this.currentLevel.registerObjectCreatedEvent(handler);
    }
}
