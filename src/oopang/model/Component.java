package oopang.model;

public interface Component {
    
    /**
     * Initialize Component activity
     */
    void start();
    
    /**
     * Update Component status
     * 
     * @param deltaTime
     *       time elapsed since last update
     */
    void update(final double deltaTime);
    
    /**
     * Enable the Component activity
     */
    void enable();
    
    /**
     * Disable the Component activity
     */
    void disable();

}
