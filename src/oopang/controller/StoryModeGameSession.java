package oopang.controller;

import java.io.IOException;
import java.util.Optional;

import oopang.controller.loader.LevelData;
import oopang.controller.loader.LevelLoader;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.model.levels.Level;
import oopang.model.levels.LevelBuilder;
import oopang.view.View;

/**
 * 
 */
public final class StoryModeGameSession extends GameSession {

    private static final int MAX_LEVEL = 4;
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
     * @param loader
     *      the object used to create levels
     * @param levelIndex
     *      the first level to be loaded
     */
    public StoryModeGameSession(final View view, final Model model, final boolean isMultiPlayer, final LevelLoader loader, final int levelIndex) {
        super(view, model, isMultiPlayer, loader);
        this.currentLevel = levelIndex;
        this.lives = FULL_LIFE;
    }

    @Override
    public Optional<LevelData> getNextLevel(final LevelBuilder builder) throws IOException {
        if (!this.hasNextLevel()) {
            return Optional.empty();
        }
        final LevelData nextLevelData = this.getLoader().loadStoryLevel(this.currentLevel, builder);
        return Optional.of(nextLevelData);
    }

    @Override
    public void handleGameOver(final GameOverStatus status) {
        final LevelResult result = status.getResult();
        if (result == LevelResult.LEVEL_COMPLETE) {
            super.addScore(status.getScore());
            this.currentLevel++;
        }
        if (result == LevelResult.OUT_OF_TIME || result == LevelResult.PLAYER_DEAD) {
            this.lives--;
        }
        super.handleGameOver(status);
    }
    @Override
    public int getLifeCount() {
        return this.lives;
    }

    @Override
    public boolean hasNextLevel() {
        return this.lives > 0 && this.currentLevel <= MAX_LEVEL;
    }
}
