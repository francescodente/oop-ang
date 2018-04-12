package oopang.view.javafx.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oopang.view.GameScene;

/**
 * Describes the controller for the Game Over scene.
 */
public final class GameOverController extends SceneController {

    @Override
    protected GameScene getNextScene() {
        return GameScene.LEADERBOARD;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.nextScene();
        }
    }
}
