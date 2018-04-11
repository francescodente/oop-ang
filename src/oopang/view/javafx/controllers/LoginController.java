package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import oopang.view.GameScene;

/**
 *
 */
public final class LoginController extends SceneController {

    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;


    @Override
    protected GameScene getNextScene() {
        return GameScene.SELECT_PLAYERS;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

    /**
     * Method that load the user that already exist.
     */
    public void userLogin() {
        if (this.getController().loginUser(userName.getText(), userPassword.getText())) {
            nextScene();
        } else {
            this.getView().getDialogFactory().createUserNotFound(userName.getText()).show();
        }
    }

    /**
     * Method that register the new user.
     */
    public void userRegister() {
        if (this.getController().registerUser(userName.getText(), userPassword.getText())) {
            nextScene();
        } else {
            this.getView().getDialogFactory().createFailedToRegisterUser(userName.getText()).show();
        }
    }

}
