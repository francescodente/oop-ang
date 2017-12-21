package oopang.view;

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
    void loadScene(final GameScene scene);
    
    /**
     * 
     */
    void showPauseMenu();
    
    //TODO event impl

}
