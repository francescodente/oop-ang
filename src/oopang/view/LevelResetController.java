package oopang.view;

/**
 * This class implements the scene LevelReset.
 */
public final class LevelResetController extends SceneController {

    @Override
    protected void nextScene() {
        this.getController().continueGameSession();
    }

}
