package oopang.controller;

import java.util.EnumMap;
import java.util.Map;

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
     * @param level
     *      the {@link Level} to start.
     */
    protected final void startNewLevel(final Level level) {
        final Map<PlayerTag, InputWriter> inputMap = new EnumMap<>(PlayerTag.class);
        final InputController input = new InputController();
        inputMap.put(PlayerTag.PLAYER_ONE, input);
        Level current = new SinglePlayerLevel(level, input);
        if (this.isMultiPlayer) {
            final InputController inputPlayerTwo = new InputController();
            inputMap.put(PlayerTag.PLAYER_TWO, inputPlayerTwo);
            current = new SinglePlayerLevel(current, inputPlayerTwo);
        }
        current.registerGameOverEvent(this::handleGameOver);
        this.gameloop = new GameLoop(this.scene, this.world, inputMap);
        this.world.setCurrentLevel(current);
    }

    /**
     * Contains the logic for when the level ends.
     * @param status
     *      the status of the level.
     */
    protected void handleGameOver(final GameOverStatus status) {
        this.score += status.getScore();
    }
}
