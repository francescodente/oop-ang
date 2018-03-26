package oopang.controller;

import java.util.Optional;

import oopang.controller.loader.LevelData;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.view.View;

/**
 * 
 */
public final class StoryModeGameSession extends GameSession {

    private static final int MAX_LEVEL = 17;
    private static final int FULL_LIFE = 5;
    private int currentLevel;
    private int lives;

    /**
     * Create a new StoryModeGameSession instance.
     * @param view
     *      the view Reference
     * @param model
     *      the model reference
     * @param isMultiPlayer
     *      true if is a multiplayer session
     * @param levelIndex
     *      the first level to be loaded
     */
    public StoryModeGameSession(final View view, final Model model, final boolean isMultiPlayer, final int levelIndex) {
        super(view, model, isMultiPlayer);
        this.currentLevel = levelIndex;
        this.lives = FULL_LIFE;
    }

    @Override
    public Optional<LevelData> getNextLevel() throws Exception {
        if (this.lives <= 0 || this.currentLevel >= MAX_LEVEL) {
            return Optional.empty();
        }
        return Optional.of(this.getLoader().loadStoryLevel(this.currentLevel));
    }

    @Override
    protected void handleGameOver(final GameOverStatus status) {
        final LevelResult result = status.getResult();
        if (result == LevelResult.LEVEL_COMPLETE) {
            super.addScore(status.getScore());
            this.currentLevel++;
        }
        if (result == LevelResult.OUT_OF_TIME || result == LevelResult.PLAYER_DEAD) {
            this.lives--;
        }
    }

}
