package oopang.view;

import oopang.controller.Controller;
import oopang.view.dialogs.DialogFactory;

/**
 * View of the application in the MVC pattern.
 */
public interface View {

    /**
     * Makes the UI visible and sets the controller to be used.
     * @param controller
     *      the {@link Controller} object.
     */
    void launch(Controller controller);

    /**
     * Tells the view to draw the current state of the game.
     */
    void render();

    /**
     * Changes the currently visualized menu.
     * @param scene
     *      value for the selected scene to be loaded.
     */
    void loadScene(GameScene scene);

    /**
     * Returns the DialogFactory.
     * @return
     *      the dialogFactory
     */
    DialogFactory getDialogFactory();
}
