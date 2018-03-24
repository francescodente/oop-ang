package oopang.view;

import oopang.controller.Controller;
import oopang.view.dialogs.Dialog;
import oopang.view.dialogs.DialogFactory;

/**
 * View of the application.
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
     * Shows the pause menu as an overlay of the game view.
     */
    void showPauseMenu();

    /**
     * Shows the given dialog.
     * @param dialog
     *      the dialog to be shown
     */
    void showDialog(Dialog dialog);

    /**
     * Returns the DialogFactory.
     * @return
     *      the dialogFactory
     */
    DialogFactory getDialogFactory();
}
