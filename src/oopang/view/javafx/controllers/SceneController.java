package oopang.view.javafx.controllers;

import oopang.controller.Controller;
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
     * Method getter of the View.
     * @return
     *      the view.
     */
    protected View getView() {
        return this.view;
    }

    /**
     * Abstract Method that renders the menu.
     * The implementation is empty because no all the class uses this method.
     */
    public void render() { }

    /**
     * Abstract method that depends on the scene selected.
     */
    protected abstract void nextScene(); 

}
