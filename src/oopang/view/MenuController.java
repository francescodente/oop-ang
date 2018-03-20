package oopang.view;

import oopang.controller.Controller;

/**
 * This represent the Abstract Class that define different Menu scenes.
 */
public abstract class MenuController {

    private final Controller controller;
    private final View view;

    /**
     * The constructor that create the MenuController.
     * @param controller
     *      the controller of the game.
     * @param view
     *      the view of the game.
     */
    public MenuController(final Controller controller, final View view) {
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

    protected abstract void nextScene(); 

}
