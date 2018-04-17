package oopang.view.javafx.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import oopang.commons.PlayerTag;
import oopang.controller.Controller;
import oopang.model.Model;
import oopang.model.gameobjects.AbstractGameObjectVisitor;

import oopang.model.gameobjects.Player;

import oopang.model.input.InputDirection;
import oopang.model.levels.Level;
import oopang.model.powers.Power;
import oopang.model.powers.PowerTag;
import oopang.model.powers.TimedPower;
import oopang.view.GameScene;
import oopang.view.View;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.javafx.JavaFXCanvasDrawer;
import oopang.view.rendering.javafx.JavaFXUIFactory;

/**
 * Class implementing the real scene of the game.
 */
public final class GameController extends SceneController {

    private Level level;
    @FXML
    private Canvas canvas;
    @FXML
    private BorderPane timebarContainer;
    @FXML
    private Pane canvasContainer;
    @FXML
    private Pane statusBarContainer;
    @FXML
    private Pane player1Powers;
    @FXML
    private Pane player2Powers;
    @FXML
    private Pane player1Shooter;
    @FXML
    private Pane player2Shooter;
    @FXML
    private Label score;
    @FXML
    private Pane livesContainer;
    @FXML
    private StackPane stackpanel;
    private CanvasDrawer canvasDrawer;
    private JavaFXUIFactory iconFactory;
    private boolean isInPause;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.iconFactory = new JavaFXUIFactory();
        for (int j = 0; j < this.getController().getLifeCount(); j++) {
            final Node icon = this.iconFactory.createHeartIcon();
            this.livesContainer.getChildren().add(icon);
        }
        this.resetGameCanvasCoordinates();
        this.getController().registerLevelStartedEvent(i -> {
            this.canvasDrawer = new JavaFXCanvasDrawer(this.canvas, i.getWallTexture());
            final Renderer background = canvasDrawer.getRendererFactory().createBackgroundRenderer(i.getTime(), i.getBackground());
            this.canvasDrawer.addRenderer(background);
            this.level = i.getLevel();
            this.level.getObjectCreatedEvent().register(o -> {
                o.accept(new AbstractGameObjectVisitor<Void>(null) {
                    @Override
                    public Void visit(final Player player) {
                        player.getPickupCollectedEvent().register(p -> handlePickupEvent(p, player));
                        return null;
                    }
                });
                final Renderer object = this.canvasDrawer.getRendererFactory().createGameObjectRenderer(o);
                o.getDestroyedEvent().register(r -> this.canvasDrawer.removeRenderer(object));
            });
            if (this.level.getRemainingTime() != 0) {
                Platform.runLater(() -> this.timebarContainer.setCenter(iconFactory.createTimeBar(this.level)));
            }
        }); 
        this.canvasContainer.widthProperty().addListener(w -> this.resizeCanvas());
        this.canvasContainer.heightProperty().addListener(h -> this.resizeCanvas());
        this.statusBarContainer.prefWidthProperty().bind(this.canvas.widthProperty());
        this.getController().continueGameSession();
        this.isInPause = false;
    }

    @Override
    @FXML
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.SPACE) {
            this.getController().sendCommand(e -> e.setShooting(true), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.A) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.D) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.CONTROL) {
            this.getController().sendCommand(e -> e.setShooting(true), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.P) {
            final ObservableList<Node> childs = stackpanel.getChildren();
            final Node topNode = childs.get(childs.size() - 1);
            topNode.toBack();
            if (!this.isInPause) {
                this.getController().pauseGame();
                childs.get(childs.size() - 1).setVisible(true);
                this.isInPause = true;
            } else {
                this.getController().resume();
                childs.get(0).setVisible(false);
                this.isInPause = false;
            }
        } else if (event.getCode() == KeyCode.Q) {
            this.getController().forceCloseGameSession();
        }
    }

    /**
     * Method to handle the ReleasedKeyEvent.
     * @param event
     *      the key released
     */
    @FXML
    public void handleReleased(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.LEFT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.RIGHT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.SPACE) {
            this.getController().sendCommand(e -> e.setShooting(false), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.A) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.LEFT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.D) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.RIGHT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.CONTROL) {
            this.getController().sendCommand(e -> e.setShooting(false), PlayerTag.PLAYER_TWO);
        }
    }

    /**
     * Renders the Scene.
     */
    @Override
    public void render() {
        this.score.setText(Integer.toString(this.level.getScore() + this.getController().getCurrentTotalScore()));
        canvasDrawer.draw();
    }

    @Override
    protected GameScene getNextScene() {
        return GameScene.GAMEOVER;
    }

    @Override
    protected GameScene getPreviousScene() {
        return GameScene.MAIN_MENU;
    }

    private void resizeCanvas() {
        final double parentWidth = this.canvasContainer.getWidth();
        final double parentHeight = this.canvasContainer.getHeight();
        final double ratio = parentWidth / parentHeight;
        final double expectedRatio = Model.TOTAL_WIDTH / Model.TOTAL_HEIGHT;
        if (ratio < expectedRatio) {
            this.canvas.setWidth(parentWidth);
            this.canvas.setHeight(parentWidth / expectedRatio);
        } else {
            this.canvas.setHeight(parentHeight);
            this.canvas.setWidth(parentHeight * expectedRatio);
        }
        this.canvas.getGraphicsContext2D().restore();
        this.resetGameCanvasCoordinates();
        this.render();
    }

    private void resetGameCanvasCoordinates() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.save();
        final double canvasWidth = this.canvas.getWidth();
        final double canvasHeight = this.canvas.getHeight();
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.scale(1, -1);
        gc.translate(canvasWidth / 2, -canvasHeight);
        gc.scale(canvasWidth / (Model.WORLD_WIDTH + Model.WALL_WIDTH * 2),
                canvasHeight / (Model.WORLD_HEIGHT + Model.WALL_WIDTH * 2));
        gc.translate(0, Model.WALL_WIDTH);
    }

    private void handlePickupEvent(final Power power, final Player player) {
        final Pane toBeUsedPowerPane = (player.getPlayerTag() == PlayerTag.PLAYER_ONE) ? player1Powers : player2Powers;
        if (power instanceof TimedPower) {
            final Node icon = this.iconFactory.createTimedIcon((TimedPower) power);
            Platform.runLater(() -> toBeUsedPowerPane.getChildren().add(icon));
            ((TimedPower) power).getTimeOutEvent().register(n -> Platform.runLater(() -> toBeUsedPowerPane.getChildren().remove(icon)));
        }
        final Pane toBeUsedShooterPane = (player.getPlayerTag() == PlayerTag.PLAYER_ONE) ? player1Shooter : player2Shooter;
        if (power.getPowertag() == PowerTag.DOUBLESHOT || power.getPowertag() == PowerTag.ADHESIVESHOT) {
            final ImageView icon = this.iconFactory.createPowerIcon(power.getPowertag());
            icon.fitHeightProperty().bind(toBeUsedShooterPane.heightProperty());
            Platform.runLater(() -> toBeUsedShooterPane.getChildren().add(icon));
        }
    }
}
