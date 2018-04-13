package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import oopang.view.GameScene;

/**
 * This class implements the scene UserProfile.
 */
public final class UserProfileController extends SceneController{

    @Override
    protected GameScene getNextScene() {
        return GameScene.SELECT_PLAYERS;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.SELECT_PLAYERS;
    }

    /**
     * Method that allow the user to logout.
     */
    @FXML
    public void logout() {
        this.getView().loadScene(GameScene.MAIN_MENU);
    }

}
