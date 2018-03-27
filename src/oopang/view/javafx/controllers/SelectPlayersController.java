package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import oopang.view.GameParameters;
import oopang.view.GameScene;

/**
 * This class implements the scene SelectPlayers.
 */
public final class SelectPlayersController extends SceneController {

    /**
     * Method that load the next scene from the selection Single Player.
     */
    @FXML
    public void selectSinglePlayer() {
        GameParameters.setMultiplayer(false);
        this.nextScene();
    }

    /**
     * Method that load the next scene from the selection MultiPlayer.
     */
    @FXML
    public void selectMultiPlayer() {
        GameParameters.setMultiplayer(true);
        this.nextScene();
    }

    @Override
    protected GameScene getNextScene() {
        return GameScene.SELECT_MODE;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

}
