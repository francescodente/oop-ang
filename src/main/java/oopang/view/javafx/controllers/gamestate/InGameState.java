package oopang.view.javafx.controllers.gamestate;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oopang.commons.PlayerTag;
import oopang.controller.Controller;
import oopang.model.input.InputDirection;
import oopang.view.javafx.controllers.GameGuiController;

/**
 * Represents the state where the player is interacting with the game.
 */
public final class InGameState extends GameGUIState {

    /**
     * Creates a new in game state.
     * @param gameGui
     *      the gui controller.
     * @param controller
     *      the application controller.
     */
    public InGameState(final GameGuiController gameGui, final Controller controller) {
        super(gameGui, controller);
    }

    @Override
    public void onStateEntry() {

    }

    @Override
    public void onStateExit() {

    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.SPACE) {
            this.getController().sendCommand(e -> e.setShooting(true), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.A) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.LEFT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.D) {
            this.getController().sendCommand(e -> e.setDirection(InputDirection.RIGHT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.CONTROL) {
            this.getController().sendCommand(e -> e.setShooting(true), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.P) {
            this.getGameGui().setState(this.getGameGui().getPausedState());
        }
    }

    @Override
    public void onKeyReleased(final KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.LEFT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.RIGHT), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.SPACE) {
            this.getController().sendCommand(e -> e.setShooting(false), PlayerTag.PLAYER_ONE);
        } else if (event.getCode() == KeyCode.A) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.LEFT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.D) {
            this.getController().sendCommand(e -> e.removeDirection(InputDirection.RIGHT), PlayerTag.PLAYER_TWO);
        } else if (event.getCode() == KeyCode.CONTROL) {
            this.getController().sendCommand(e -> e.setShooting(false), PlayerTag.PLAYER_TWO);
        }
    }
}
