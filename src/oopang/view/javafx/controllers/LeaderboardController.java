package oopang.view.javafx.controllers;


import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.controller.leaderboard.Leaderboard;
import oopang.controller.leaderboard.LeaderboardRecord;
import oopang.view.GameParameters;
import oopang.view.GameScene;
import oopang.view.View;

/**
 *
 */
public class LeaderboardController extends SceneController {
    @FXML
    private TableView<LeaderboardRecord> table;
    @FXML
    private TableColumn<LeaderboardRecord, Integer> rankColumn;
    @FXML
    private TableColumn<LeaderboardRecord, String> nameColumn;
    @FXML
    private TableColumn<LeaderboardRecord, Integer> stageColumn;
    @FXML
    private TableColumn<LeaderboardRecord, Integer> scoreColumn;

    @Override
    protected GameScene getNextScene() {
        return GameScene.MAIN_MENU;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        final Leaderboard leaderboard = this.getLeaderboard();
        final List<LeaderboardRecord> records = leaderboard.getRecords().collect(Collectors.toList());
        this.nameColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getName()));
        this.stageColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getStage()));
        this.scoreColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getScore()));
        this.rankColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(records.indexOf(p.getValue()) + 1));
        this.table.getItems().setAll(records);
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.Q) {
            this.nextScene();
        }
    }

    private Leaderboard getLeaderboard() {
        return this.getController().getLeaderboard();
    }

    @FXML
    public void checkRestart() {
        if (GameParameters.getIfStoryMode()) {
            this.getController().startStoryGameSession(GameParameters.getLevelindex(), GameParameters.isMultiplayer());
            this.getView().loadScene(GameScene.GAME_GUI);
        }
        else {
            this.getController().startInifiniteGameSession(GameParameters.isMultiplayer());
            this.getView().loadScene(GameScene.GAME_GUI);
        }
    }
}
