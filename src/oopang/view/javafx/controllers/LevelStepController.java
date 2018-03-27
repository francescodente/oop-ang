package oopang.view.javafx.controllers;

import oopang.view.GameScene;

/**
 * This class implements the scene LevelStep after a win.
 */
public final class LevelStepController extends SceneController {

    @Override
    protected GameScene getNextScene() {
        return GameScene.GAME_GUI;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

}
