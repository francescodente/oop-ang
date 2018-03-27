package oopang.view.javafx.controllers;

import oopang.view.GameScene;

/**
 * This class implements the scene LevelReset.
 */
public final class LevelResetController extends SceneController {

    @Override
    protected GameScene getNextScene() {
        return GameScene.GAME_GUI;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

}
