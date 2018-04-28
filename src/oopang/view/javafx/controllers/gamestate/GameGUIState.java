package oopang.view.javafx.controllers.gamestate;

import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.view.javafx.controllers.GameController;

/**
 * Represents a generic state of the GameGui scene.
 */
public abstract class GameGUIState {

    private final GameController gameGui;
    private final Controller controller;

    /**
     * Initializes this state with the given {@link GameController}.
     * @param gameGui
     *      the {@link GameController} object.
     * @param controller
     *      the {@link Controller} of the app.
     */
    public GameGUIState(final GameController gameGui, final Controller controller) {
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
     *      the {@link GameController}.
     */
    protected GameController getGameGui() {
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
