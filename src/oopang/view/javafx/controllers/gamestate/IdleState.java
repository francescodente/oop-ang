package oopang.view.javafx.controllers.gamestate;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import oopang.controller.Controller;
import oopang.view.javafx.controllers.GameController;

/**
 * Represents the idle state of the level, active at the start of the new level to give
 * the user some time to prepare before playing.
 */
public final class IdleState extends GameGUIState {

    private boolean keyPressed;
    private final Pane startMessage;
    /**
     * Creates a new idle state object.
     * @param gameGui
     *      the gui controller.
     * @param controller
     *      the application controller.
     * @param startMessage
     *      the pane containing the "Ready" message.
     */
    public IdleState(final GameController gameGui, final Controller controller, final Pane startMessage) {
        super(gameGui, controller);
        this.keyPressed = false;
        this.startMessage = startMessage;
    }

    @Override
    public void onStateEntry() {
        this.getController().pauseGame();
        this.startMessage.setVisible(true);
    }

    @Override
    public void onStateExit() {
        this.getController().resume();
        this.startMessage.setVisible(false);
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        this.keyPressed = true;
    }

    @Override
    public void onKeyReleased(final KeyEvent event) {
        if (this.keyPressed) {
            this.getGameGui().setState(this.getGameGui().getInGameState());
        }
    }

}
