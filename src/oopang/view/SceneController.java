package oopang.view;

import javafx.scene.Scene;
import oopang.controller.Controller;

/**
 * This represent the Abstract Class that define different Menu scenes.
 */
public abstract class SceneController {

    private final Controller controller;
    private final View view;

    /**
     * The constructor that create the MenuController.
     * @param controller
     *      the controller of the game.
     * @param view
     *      the view of the game.
     */
    public SceneController(final Controller controller, final View view, final Scene scene) {
        this.controller = controller;
        this.view = view;
    }

    /**
     * Method getter of the Controller.
     * @return
     *      the controller.
     */
    protected Controller getController() {
        return this.controller;
    }

    /**
     * Method getter of the View.
     * @return
     *      the view.
     */
    protected View getView() {
        return this.view;
    }

    /**
     * Abstract Method that renders the menu.
     */
    public abstract void render();

    /**
     * Abstract method that depends on the scene selected.
     */
    protected abstract void nextScene(); 

}
