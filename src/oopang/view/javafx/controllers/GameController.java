package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import oopang.commons.PlayerTag;
import oopang.controller.Controller;
import oopang.model.Model;
import oopang.model.input.InputDirection;
import oopang.view.GameScene;
import oopang.view.View;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.javafx.JavaFXCanvasDrawer;

/**
 * Class implementing the real scene of the game.
 */
public final class GameController extends SceneController {

    @FXML
    private Canvas canvas;
    @FXML
    private Pane canvasContainer;
    private CanvasDrawer canvasDrawer;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.canvasDrawer = new JavaFXCanvasDrawer(this.canvas);
        this.resetGameCanvasCoordinates();
        this.canvas.requestFocus();
        this.getController().registerLevelStartedEvent(i -> {
            final Renderer background = canvasDrawer.getRendererFactory(i.getWallTexture()).createBackgroundRenderer(i.getTime(), i.getBackground());
            this.canvasDrawer.addRenderer(background);
            i.getLevel().getObjectCreatedEvent().register(o -> {
                final Renderer object = this.canvasDrawer.getRendererFactory(i.getWallTexture()).createGameObjectRenderer(o);
                this.canvasDrawer.addRenderer(object);
                o.getDestroyedEvent().register(r -> this.canvasDrawer.removeRenderer(object));
            });
        });
        this.canvasContainer.widthProperty().addListener(w -> this.resizeCanvas());
        this.canvasContainer.heightProperty().addListener(h -> this.resizeCanvas());
        this.getController().continueGameSession();
    }

    /**
     * Method to handle input from players.
     * @param event
     *      the key pressed
     */
    @FXML
    public void handlePressed(final KeyEvent event) {
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
}
