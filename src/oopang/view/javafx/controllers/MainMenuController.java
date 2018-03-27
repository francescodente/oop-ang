package oopang.view.javafx.controllers;

import oopang.view.GameScene;

/**
 * This class implements the scene MainMenu.
 */
public final class MainMenuController extends SceneController {

    /**
     * Method that load the next scene from the View.
     */
    public void nextScene() {
        this.getView().loadScene(GameScene.SELECT_PLAYERS);
    }

}
