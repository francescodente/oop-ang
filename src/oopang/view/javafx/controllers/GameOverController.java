package oopang.view.javafx.controllers;

import oopang.view.GameScene;

/**
 * Describes the controller for the Game Over scene.
 */
public class GameOverController extends SceneController {

    @Override
    protected GameScene getNextScene() {
        return GameScene.MAIN_MENU; // TODO: Change to Leaderboard.
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

}
