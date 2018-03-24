package oopang.view;

import java.awt.event.KeyEvent;

import oopang.commons.Command;
import oopang.controller.PlayerTag;
import oopang.model.input.InputDirection;
import oopang.model.input.InputWriter;
import oopang.view.rendering.CanvasDrawer;

/**
 * Class implementing the real scene of the game.
 */
public class GameController extends SceneController {

    private CanvasDrawer canvasDrawer;
    private InputWriter input;

    
    public GameController(CanvasDrawer canvasDrawer, InputWriter input) {
        super();
        this.canvasDrawer = canvasDrawer;
        this.input = input;
    }

    @Override
    protected void nextScene() {
        // TODO Auto-generated method stub
        this.getController().closeGameSession();
    }

    public void handleInput(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            input.setDirection(InputDirection.LEFT);
            this.getController().sendCommand(, PlayerTag.PLAYER_ONE);
        }
    }
    
    public void render() {
        canvasDrawer.addRenderer(rend);
    }
}
