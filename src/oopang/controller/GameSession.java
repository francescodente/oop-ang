package oopang.controller;

import java.util.EnumMap;
import java.util.Map;

import oopang.commons.events.Event;
import oopang.commons.events.EventHandler;
import oopang.controller.loader.LevelData;
import oopang.controller.loader.LevelLoader;
import oopang.controller.loader.TestLevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.Model;
import oopang.model.input.InputController;
import oopang.model.input.InputWriter;
import oopang.model.levels.Level;
import oopang.model.levels.SinglePlayerLevel;
import oopang.view.View;

/**
 * Class including informations about the current session game.
 */
public abstract class GameSession {

    private GameLoop gameloop;
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
    public abstract void continueGame();

    /**
     * Utility method for subclasses to start a new game loop and run the given level.
     * @param leveldata
     *      the {@link LevelData} to start.
     */
    protected final void startNewLevel(final LevelData leveldata) {
        final Map<PlayerTag, InputWriter> inputMap = new EnumMap<>(PlayerTag.class);
        final InputController input = new InputController();
        inputMap.put(PlayerTag.PLAYER_ONE, input);
        Level current = new SinglePlayerLevel(leveldata.getLevel(), input);
        if (this.isMultiPlayer) {
            final InputController inputPlayerTwo = new InputController();
            inputMap.put(PlayerTag.PLAYER_TWO, inputPlayerTwo);
            current = new SinglePlayerLevel(current, inputPlayerTwo);
        }
        current.registerGameOverEvent(this::handleGameOver);
        this.gameloop = new GameLoop(this.scene, this.world, inputMap);
        this.world.setCurrentLevel(current);
        //TODO send levelData to view
    }

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
     * Method that triggers the shouldEnd status.
     * @param status
     *      true if the session should end
     */
    protected void shouldEnd(final Boolean status) {
        this.shouldEnd.trigger(status);
    }
}
