package oopang.view.javafx;

import javafx.scene.Scene;
import oopang.view.javafx.controllers.SceneController;

/**
 * This object wraps a Scene with its associated controller.
 */
public class SceneWrapper {

    private final Scene scene;
    private final SceneController controller;

    /**
     * Create a new Scene Wrapper.
     * @param scene
     *      the scene object
     * @param controller
     *      the controller associated controller
     */
    public SceneWrapper(final Scene scene, final SceneController controller) {
        this.scene = scene;
        this.controller = controller;
    }

    /**
     * Return the Scene.
     * @return
     *      the Scene object
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Returns the controller of the Scene.
     * @return
     *      the Scene Controller.
     */
    public SceneController getController() {
        return this.controller;
    }

}
