package oopang.view.javafx.controllers;

import oopang.view.GameParameters;
import oopang.view.GameScene;

/**
 * This class implements the scene SelectMode.
 */
public final class SelectModeController extends SceneController {

    private static final int START_LEVEL_INDEX = 1;

    @Override
    protected void nextScene() {
        this.getView().loadScene(GameScene.SELECT_LEVEL);
    }

    /**
     * Method that load the game from the selection Infinite Mode.
     */
    public void startInfiniteGameChoose() {
        this.getController().startInifiniteGameSession(GameParameters.isMultiplayer());
    }

    /**
     * Method that load the next scene from the selection Story Mode.
     */
    public void storyModeChoose() {
        GameParameters.setLevelindex(START_LEVEL_INDEX);
        this.getController().startStoryGameSession(GameParameters.getLevelindex(), GameParameters.isMultiplayer());
    }

}
