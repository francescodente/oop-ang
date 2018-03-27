package oopang.view.javafx.controllers;

import oopang.view.GameScene;

/**
 * This class implements the scene MainMenu.
 */
public final class MainMenuController extends SceneController {

    @Override
    protected GameScene getNextScene() {
        return GameScene.SELECT_PLAYERS;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

}
