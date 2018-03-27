package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import oopang.controller.Controller;
import oopang.view.View;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.Renderer;
import oopang.view.rendering.javafx.JavaFXCanvasDrawer;

/**
 * Class implementing the real scene of the game.
 */
public class GameController extends SceneController {

    @FXML
    private Canvas canvas;
    private CanvasDrawer canvasDrawer;

    @Override
    public void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.canvasDrawer = new JavaFXCanvasDrawer(this.canvas);
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
     * Renders the Scene.
     */
    @Override
    public void render() {
        canvasDrawer.draw();
    }
}
