package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import oopang.view.GameParameters;
import oopang.view.GameScene;

/**
 * This class implements the scene SelectMode.
 */
public final class SelectModeController extends SceneController {
    /**
     * Method that loads the game from the selection Infinite Mode.
     */
    @FXML
    public void startInfiniteGameChoose() {
        this.getController().startInifiniteGameSession(GameParameters.isMultiplayer());
        GameParameters.setIsStoryMode(false);
        this.nextScene();
    }

    /**
     * Method that loads the next scene from the selection Story Mode.
     */
    @FXML
    public void storyModeChoose() {
        GameParameters.setIsStoryMode(true);
        this.getView().loadScene(GameScene.SELECT_LEVEL);
    }

    @Override
    protected GameScene getNextScene() {
        return GameScene.GAME_GUI;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.SELECT_PLAYERS;
    }

}
