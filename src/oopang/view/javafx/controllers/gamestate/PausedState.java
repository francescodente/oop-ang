package oopang.view.javafx.controllers.gamestate;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import oopang.controller.Controller;
import oopang.view.javafx.controllers.GameController;

/**
 * 
 */
public class PausedState extends GameGUIState {

    private final Pane pausePane;
    /**
     * 
     * @param gameGui
     * @param controller
     * @param pausePane
     */
    public PausedState(final GameController gameGui, final Controller controller, final Pane pausePane) {
        super(gameGui, controller);
        this.pausePane = pausePane;
    }

    @Override
    public void onStateEntry() {
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
