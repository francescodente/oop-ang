package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import oopang.view.GameParameters;
import oopang.view.GameScene;

/**
 * This class implements the scene SelectMode.
 */
public final class SelectModeController extends SceneController {

    private static final int START_LEVEL_INDEX = 1;

    /**
     * Method that loads the game from the selection Infinite Mode.
     */
    @FXML
    public void startInfiniteGameChoose() {
        this.getController().startInifiniteGameSession(GameParameters.isMultiplayer());
    }

    /**
     * Method that loads the next scene from the selection Story Mode.
     */
    @FXML
    public void storyModeChoose() {
        GameParameters.setLevelindex(START_LEVEL_INDEX);
        this.getController().startStoryGameSession(GameParameters.getLevelindex(), GameParameters.isMultiplayer());
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
