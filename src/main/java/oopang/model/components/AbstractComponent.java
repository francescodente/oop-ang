package oopang.model.components;

import oopang.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface.
 * Defines base behavior that all components share.
 */
public abstract class AbstractComponent implements Component {

    private boolean isActive;
    private final GameObject object;

    /**
     * Initializes default data for this component.
     * @param obj
     *      the game object this component is attached to.
     */
    public AbstractComponent(final GameObject obj) {
        this.isActive = true;
        this.object = obj;
    }

    @Override
    public void start() {
        // Start is implemented but left empty since not all components may need to override it.
    }

    @Override
    public void update(final double deltaTime) {
        // Update is implemented but left empty since not all components may need to override it.
    }

    @Override
    public final void enable() {
        this.isActive = true;
    }

    @Override
    public final void disable() {
        this.isActive = false;
    }

    @Override
    public final boolean isEnabled() {
        return this.isActive;
    }

    /**
     * Gets the game object this component is attached to.
     * @return
     *      the game object.
     */
    protected GameObject getGameObject() {
        return this.object;
    }
}
