package oopang.view.javafx.controllers.gamestate;

import javafx.scene.input.KeyEvent;
import oopang.controller.Controller;
import oopang.view.javafx.controllers.GameController;

/**
 * Represents the idle state of the level, active at the start of the new level to give
 * the user some time to prepare before playing.
 */
public class IdleState extends GameGUIState {

    private boolean keyPressed;
    /**
     * 
     * @param gameGui
     * @param controller
     */
    public IdleState(final GameController gameGui, final Controller controller) {
        super(gameGui, controller);
        this.keyPressed = false;
    }

    @Override
    public void onStateEntry() {
        this.getController().pauseGame();
    }

    @Override
    public void onStateExit() {
        this.getController().resume();
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
