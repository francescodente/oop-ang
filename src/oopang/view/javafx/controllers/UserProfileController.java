package oopang.view.javafx.controllers;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import oopang.controller.Controller;
import oopang.controller.users.User;
import oopang.controller.users.UserStat;
import oopang.controller.users.Users;
import oopang.model.powers.PowerTag;
import oopang.view.GameScene;
import oopang.view.View;
import oopang.view.rendering.javafx.JavaFXUIFactory;

/**
 * This class implements the scene UserProfile.
 */
public final class UserProfileController extends SceneController {

    @FXML
    private Label userName;
    @FXML
    private Label xpPoints;
    @FXML
    private Label coins;
    @FXML
    private Label levelRank;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private VBox powerContainerBox;
    @FXML
    private Label maxArcadeStage;
    @FXML
    private Label maxSurvivalStage;
    @FXML
    private Label maxArcadeScore;
    @FXML
    private Label maxSurvivalScore;

    private JavaFXUIFactory factory;
    private User user;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.user = controller.getUser().get();
        this.userName.setText(this.user.getName());
        this.xpPoints.setText(Users.computeExceedingXpPoints(this.user) + " xp");
        this.levelRank.setText("Rank: " + Users.computeRank(this.user));
        this.maxArcadeStage.setText("Max Arcade Stage: " + this.user.getStatValue(UserStat.MAX_ARCADE_STAGE));
        this.maxSurvivalStage.setText("Max Survival Stage: " + this.user.getStatValue(UserStat.MAX_SURVIVAL_STAGE));
        this.maxSurvivalScore.setText("Max Survival Score: " + this.user.getStatValue(UserStat.MAX_SURVIVAL_SCORE));
        this.maxArcadeScore.setText("Max Arcade Score: " + this.user.getStatValue(UserStat.MAX_ARCADE_SCORE));
        this.xpBar.setProgress(Users.computeXpLevelPercentage(this.user));
        this.factory = new JavaFXUIFactory();
        this.user.getUserChangedEvent().register(n -> {
            Platform.runLater(() -> this.refresh(n));
        });
        refresh(this.user);
    }

    /**
     * Refresh all the values in the screen.
     */
    private void refresh(final User user) {
        this.powerContainerBox.getChildren().clear();
        this.coins.setText("Coins: " + this.user.getCoins() + "$");
        Arrays.stream(PowerTag.values()).forEach(tag -> {
            this.powerContainerBox.getChildren().add(this.factory.createUpdatePowerLevelBlock(this.user, tag));
        });
    }

    @Override
    protected GameScene getNextScene() {
        return GameScene.SELECT_PLAYERS;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.SELECT_PLAYERS;
    }

    /**
     * Method that allow the user to logout.
     */
    @FXML
    public void logout() {
        this.getController().logoutUser();
        this.getView().loadScene(GameScene.LOGIN);
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }

}
