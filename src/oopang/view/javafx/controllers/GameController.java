package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import oopang.controller.Controller;
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
    
    public void handleInput() {
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
