package oopang.view;

import oopang.controller.Controller;

public class MainMenuController extends MenuController {

    public MainMenuController(Controller controller, View view) {
        super(controller, view);
    }

    public void nextScene() {
        this.getView().loadScene(GameScene.SELECT_PLAYERS);
    }

}
