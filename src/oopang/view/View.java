package oopang.view;

import oopang.model.gameobjects.GameObject;

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

    /**
     * Create the new GameObject renderer and register to its destruction.
     * @param obj
     *      the new GameObject added to the level
     */
    void notifyNewGameObject(GameObject obj);

    //TODO event impl

}
