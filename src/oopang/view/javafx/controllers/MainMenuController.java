package oopang.view.javafx.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            this.nextScene();
        }
    }
}
