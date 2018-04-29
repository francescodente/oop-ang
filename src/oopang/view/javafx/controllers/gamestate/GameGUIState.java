package oopang.view.javafx.controllers.gamestate;

import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.view.javafx.controllers.GameGuiController;

/**
 * Represents a generic state of the GameGui scene.
 */
public abstract class GameGUIState {

    private final GameGuiController gameGui;
    private final Controller controller;

    /**
     * Initializes this state with the given {@link GameGuiController}.
     * @param gameGui
     *      the {@link GameGuiController} object.
     * @param controller
     *      the {@link Controller} of the app.
     */
    public GameGUIState(final GameGuiController gameGui, final Controller controller) {
        this.gameGui = gameGui;
        this.controller = controller;
    }

    /**
     * Method to be called whenever this state becomes the active state.
     */
    public abstract void onStateEntry();

    /**
     * Method to be called whenever this state becomes inactive.
     */
    public abstract void onStateExit();

    /**
     * Handles the event key pressed.
     * @param event
     *      the info about the key pressed.
     */
    public abstract void onKeyPressed(KeyEvent event);

    /**
     * Handles the event key released.
     * @param event
     *      the info about the key released.
     */
    public abstract void onKeyReleased(KeyEvent event);

    /**
     * Getter for the game gui for this state.
     * @return
     *      the {@link GameGuiController}.
     */
    protected GameGuiController getGameGui() {
        return this.gameGui;
    }

    /**
     * Getter for the app {@link Controller}.
     * @return
     *      the {@link Controller}.
     */
    protected Controller getController() {
        return this.controller;
    }
}
