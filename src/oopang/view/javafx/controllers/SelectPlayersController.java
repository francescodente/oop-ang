package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import oopang.controller.Controller;
import oopang.view.GameParameters;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * This class implements the scene SelectPlayers.
 */
public final class SelectPlayersController extends SceneController {

    @FXML
    private Button userbutton;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        userbutton.setVisible(controller.getUser().isPresent());
    }
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

    /**
     * Method that allow to see the user profile.
     */
    @FXML
    public void userProfile() {
        this.getView().loadScene(GameScene.USER_PROFILE);
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
