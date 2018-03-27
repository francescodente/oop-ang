package oopang.view.javafx.controllers;

/**
 * This class implements the scene LevelReset.
 */
public final class LevelResetController extends SceneController {

    @Override
    protected void nextScene() {
        this.getController().continueGameSession();
    }

}
