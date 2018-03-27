package oopang.view.javafx.controllers;

import java.awt.event.KeyEvent;

import javafx.scene.canvas.Canvas;
import oopang.controller.PlayerTag;
import oopang.model.gameobjects.GameObject;
import oopang.model.input.InputDirection;
import oopang.model.input.InputWriter;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.gameobject.GameObjectRenderer;
import oopang.view.rendering.javafx.JavaFXCanvasDrawer;

/**
 * Class implementing the real scene of the game.
 */
public class GameController extends SceneController {

    private CanvasDrawer canvasDrawer;
    private InputWriter input;

    /**
     * Constructor of the GameGuiSceneController.
     * @param input
     *      The {@link InputWriter}
     */
    public GameController(final InputWriter input) {
        super();
        //this.canvasDrawer = new JavaFXCanvasDrawer();
        this.input = input;
        this.init();
    }

    /**
     * Method that register all {@link GameObject} created and create the {@link GameObjectRenderer} related.
     */
    private void init() {
        this.getController().registerObjectCreatedEvent((e) -> {
            final Renderer object = canvasDrawer.getRendererFactory().createGameObjectRenderer(e);
            canvasDrawer.addRenderer(object);
            e.registerDestroyedEvent((r) -> {
                canvasDrawer.removeRenderer(object);
            });
        });
    }

    @Override
    protected void nextScene() {
        //this.getController().closeGameSession();
    }

    /**
     * Actions of the user.
     * @param event
     *      The Key pressed
     */
    private void handleInput(final KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            this.getController().sendCommand((e) -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_ONE);
        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.getController().sendCommand((e) -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_ONE);
        }
        if (event.getKeyCode() == KeyEvent.VK_D) {
            this.getController().sendCommand((e) -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_TWO);
        }
        if (event.getKeyCode() == KeyEvent.VK_A) {
            this.getController().sendCommand((e) -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_TWO);
        }
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            this.getController().sendCommand((e) -> e.setShooting(true), PlayerTag.PLAYER_ONE);
        }
        if (event.getKeyCode() == KeyEvent.VK_W) {
            this.getController().sendCommand((e) -> e.setShooting(true), PlayerTag.PLAYER_TWO);
        }
    }

    /**
     * Renders the Scene.
     */
    public void render() {
        canvasDrawer.draw();
    }
}
