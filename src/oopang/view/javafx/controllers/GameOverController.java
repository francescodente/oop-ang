package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * Describes the controller for the Game Over scene.
 */
public final class GameOverController extends SceneController {

    @FXML
    private Label labelScore;
    @FXML
    private Label labelName;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.labelScore.setText("Name: " + controller.getUser().get().getName());
        this.labelName.setText("Score: " + controller.getCurrentTotalScore());
    }
    
    @Override
    protected GameScene getNextScene() {
        return GameScene.LEADERBOARD;
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
