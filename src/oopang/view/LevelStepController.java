package oopang.view;

/**
 * This class implements the scene LevelStep after a win.
 */
public final class LevelStepController extends SceneController{

    @Override
    protected void nextScene() {
        this.getController().continueGameSession();
    }

}
