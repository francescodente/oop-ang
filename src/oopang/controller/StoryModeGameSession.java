package oopang.controller;

import java.io.IOException;

import org.xml.sax.SAXException;

import oopang.controller.loader.LevelData;
import oopang.model.GameOverStatus;
import oopang.model.LevelResult;
import oopang.model.Model;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * 
 */
public final class StoryModeGameSession extends GameSession {

    private static final int MAX_LEVEL = 17;
    private static final int FULL_LIFE = 5;
    private int currentLevel;
    private int lives;
    private boolean levelForward;

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
        this.levelForward = true;
    }

    @Override
    public void loadNewLevel() {
        if (!this.levelForward) {
            this.currentLevel--;
        }
        if (this.currentLevel == MAX_LEVEL) {
            this.triggerShouldEnd(true);
        } else {
            LevelData leveldata;
            try {
                leveldata = this.getLoader().loadStoryLevel(this.currentLevel);
                super.startNewLevel(leveldata);
                this.currentLevel++;
            } catch (SAXException | IOException e) {
                this.getScene().showDialog(this.getScene().getDialogFactory().createLevelNotLoaded(e));
            }
        }
    }

    @Override
    protected void handleGameOver(final GameOverStatus status) {
        final LevelResult result = status.getResult();

        if (result == LevelResult.LEVEL_COMPLETE) {
            super.addScore(status.getScore());
            this.levelForward = true;
            this.getScene().loadScene(GameScene.LEVEL_STEP);
        }

        if (result == LevelResult.OUT_OF_TIME || result == LevelResult.PLAYER_DEAD) {
            this.lives--;
            this.levelForward = false;
            this.getScene().loadScene(GameScene.LEVEL_RESET);
            if (this.lives <= 0) {
                this.triggerShouldEnd(true);
            }
        }
    }

}
