package oopang.view;


/**
 * This class implements the scene SelectPlayers.
 */
public final class SelectPlayersController extends SceneController {

    @Override
    protected void nextScene() {
        this.getView().loadScene(GameScene.SELECT_MODE);
    }

    /**
     * Method that load the next scene from the selection Single Player.
     */
    public void selectSinglePlayer() {
        GameParameters.setMultiplayer(false);
        this.nextScene();
    }

    /**
     * Method that load the next scene from the selection MultiPlayer.
     */
    public void selectMultiPlayer() {
        GameParameters.setMultiplayer(true);
        this.nextScene();
    }

}
