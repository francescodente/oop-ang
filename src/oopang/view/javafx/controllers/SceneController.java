package oopang.view.javafx.controllers;

import javafx.fxml.FXML;
import oopang.controller.Controller;
import oopang.view.GameScene;
import oopang.view.View;

/**
 * This represent the Abstract Class that define different Menu scenes.
 */
public abstract class SceneController {

    private Controller controller;
    private View view;

    /**
     * Method that initialized the controller and the view.
     * @param controller
     *      the controller of the game.
     * @param view 
     *      the controller of the view.
     */
    public void init(final Controller controller, final View view) {
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
     * Redraws the menu on the screen.
     */
    public void render() {
    }

    /**
     * Abstract method that depends on the scene selected.
     */
    @FXML
    public void nextScene() {
        this.view.loadScene(this.getNextScene());
    }

    /**
     * Returns the GameScene that needs to be loaded after this one.
     * @return
     *      The next {@link GameScene}.
     */
    protected abstract GameScene getNextScene();

    /**
     * Go to the previous scene.
     */
    @FXML
    public void back() {
        this.view.loadScene(this.getPreviousScene());
    }

    /**
     * Returns the GameScene that needs to be loaded when back is clicked.
     * @return
     *      The {@link GameScene} to load on back call.
     */
    protected abstract GameScene getPreviousScene();
}
