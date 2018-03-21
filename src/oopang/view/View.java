package oopang.view;

import oopang.controller.Controller;

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
}
