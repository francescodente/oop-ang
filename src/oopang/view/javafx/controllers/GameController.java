package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.controller.PlayerTag;
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
    private CanvasDrawer canvasDrawer;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.canvasDrawer = new JavaFXCanvasDrawer(this.canvas);
        this.getController().registerLevelStartedEvent(i -> {
            final Renderer background = canvasDrawer.getRendererFactory().createBackgroundRenderer();
            this.canvasDrawer.addRenderer(background);
            i.getLevel().registerObjectCreatedEvent(o -> {
                final Renderer object = this.canvasDrawer.getRendererFactory().createGameObjectRenderer(o);
                this.canvasDrawer.addRenderer(object);
                o.registerDestroyedEvent(r -> this.canvasDrawer.removeRenderer(object));
            });
        });
    }

    /**
     * Method to handle input from players.
     * @param event
     *      the key pressed
     */
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
        } else if (event.getCode() == KeyCode.W) {
            this.getController().sendCommand(e -> e.setShooting(true), PlayerTag.PLAYER_TWO);
        }
    }

    /**
     * Method to handle the ReleasedKeyEvent.
     * @param event
     *      the key released
     */
    public void handleReleased(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.NONE), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.SPACE) {
            this.getController().sendCommand(e -> e.setShooting(false), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.NONE), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.W) {
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
}
