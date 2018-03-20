package oopang.view;

import oopang.controller.Controller;

public class SelectPlayersController extends MenuController {

    public SelectPlayersController(Controller controller, View view) {
        super(controller, view);
    }

    @Override
    protected void nextScene() {
        this.getView().loadScene(GameScene.SELECT_MODE);
    }

    public void selectSinglePlayer() {
        GameParameters.setMultiplayer(false);
        this.nextScene();
    }

    public void selectMultiPlayer() {
        GameParameters.setMultiplayer(true);
        this.nextScene();
    }

}
