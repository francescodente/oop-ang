package oopang.view.javafx.controllers;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import oopang.controller.Controller;
import oopang.controller.users.User;
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

    private JavaFXUIFactory factory;
    private User user;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.user = controller.getUser().get();
        this.userName.setText(this.user.getName());
        this.xpPoints.setText(user.getXpPoints() + " xp");
        this.levelRank.setText("Rank: " + this.user.getRank());
        this.xpBar.setProgress(this.user.getXpLevelPercentage());
        this.factory = new JavaFXUIFactory();
        this.user.getUserModifiedEvent().register(n -> {
            Platform.runLater(() -> refresh());
        });
        refresh();
    }

    /**
     * Refresh all the values in the screen.
     */
    private void refresh() {
        this.powerContainerBox.getChildren().clear();
        this.coins.setText("Coins: " + this.user.getCoins());
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

}
