package oopang.view;

/**
 * View of the application.
 */
public interface View {

    /**
     * 
     */
    void launch();

    /**
     * 
     */
    void render();

    /**
     * 
     * @param scene
     *      value for the selected scene to be loaded
     */
    void loadScene(GameScene scene);

    /**
     * 
     */
    void showPauseMenu();

    //TODO event impl

}
