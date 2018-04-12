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
    @FXML
    public void userLogin() {
        if (checkfield()) {
            if (this.getController().loginUser(userName.getText(), userPassword.getText())) {
                nextScene();
            } else {
                this.getView().getDialogFactory().createUserNotFound(userName.getText()).show();
            }
        }
    }

    /**
     * Method that register the new user.
     */
    @FXML
    public void userRegister() {
        if (checkfield()) {
            if (this.getController().registerUser(userName.getText(), userPassword.getText())) {
                this.nextScene();
            } else {
                this.getView().getDialogFactory().createFailedToRegisterUser(userName.getText()).show();
            }
        }
    }

    private boolean checkfield() {
        if (this.userName.getText().equals("")) {
            this.getView().getDialogFactory().createEmptyFieldError("username").show();
            return false;
        } else if (this.userPassword.getText().equals("")) {
            this.getView().getDialogFactory().createEmptyFieldError("password").show();
            return false;
        }
        return true;
    }

}