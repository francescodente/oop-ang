package oopang.view.javafx.controllers.gamestate;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import oopang.commons.Command;
import oopang.commons.PlayerTag;
import oopang.controller.Controller;
import oopang.model.input.InputDirection;
import oopang.view.javafx.controllers.GameGuiController;

/**
 * Represents the state in which the game is paused.
 */
public final class PausedState extends GameGUIState {

    private final Pane pausePane;

    /**
     * Creates a new paused state object.
     * @param gameGui
     *      the gui controller.
     * @param controller
     *      the application controller.
     * @param pausePane
     *      the pane to be showed during the pause state.
     */
    public PausedState(final GameGuiController gameGui, final Controller controller, final Pane pausePane) {
        super(gameGui, controller);
        this.pausePane = pausePane;
    }

    @Override
    public void onStateEntry() {
        final Command noInputCommand = w -> {
            w.removeDirection(InputDirection.LEFT);
            w.removeDirection(InputDirection.RIGHT);
            w.setShooting(false);
        };
        this.getController().sendCommand(noInputCommand, PlayerTag.PLAYER_ONE);
        this.getController().sendCommand(noInputCommand, PlayerTag.PLAYER_TWO);
        this.getController().pauseGame();
        this.pausePane.setVisible(true);
    }

    @Override
    public void onStateExit() {
        this.getController().resume();
        this.pausePane.setVisible(false);
    }

    @Override
    public void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            this.getGameGui().setState(this.getGameGui().getInGameState());
        } else if (event.getCode() == KeyCode.Q) {
            this.getController().forceCloseGameSession();
        }
    }

    @Override
    public void onKeyReleased(final KeyEvent event) {

    }
}
