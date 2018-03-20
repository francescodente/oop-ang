package oopang.view;

import oopang.controller.Controller;

public class SelectModeController extends MenuController {

    public SelectModeController(Controller controller, View view) {
        super(controller, view);
    }

    @Override
    protected void nextScene() {
        this.getView().loadScene(GameScene.SELECT_LEVEL);
    }

    public void startInfiniteGameChoose() {
        this.getController().startInifiniteGameSession(GameParameters.isMultiplayer());
    }

    public void storyModeChoose() {
        GameParameters.setLevelindex(levelindex);
        this.nextScene();
    }

}
