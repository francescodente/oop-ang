package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import oopang.controller.Controller;
import oopang.controller.StoryModeGameSession;
import oopang.view.GameParameters;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * Controller for the level selection scene.
 */
public final class LevelSelectorController extends SceneController {

    private static final int COLUMNS = 5;

    @FXML
    private GridPane levelGrid;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        for (int i = 0; i < StoryModeGameSession.MAX_LEVEL; i++) {
            final Button button = new Button("Level " + (i + 1));
            button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            final int levelIndex = i + 1;
            button.setOnMouseClicked(e -> {
                controller.startStoryGameSession(levelIndex, GameParameters.isMultiplayer());
                GameParameters.setLevelindex(levelIndex);
                this.getView().loadScene(GameScene.GAME_GUI);
            });
//            if (i != 0) {
//                controller.getUser().ifPresent(u -> button.setDisable(u.getArcadeMaxStage() < levelIndex));
//            }
            this.levelGrid.add(button,
                    i % COLUMNS + 1, 
                    i / COLUMNS);
        }
    }

    @Override
    protected GameScene getNextScene() {
        return GameScene.GAME_GUI;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.SELECT_MODE;
    }
}
