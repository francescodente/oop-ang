package oopang.model.gameobjects;

import oopang.commons.events.EventSource;
import oopang.commons.events.Event;
import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.model.components.Component;
import oopang.model.levels.LevelManager;

/**
 * This class represents the abstract model for all GameObjects and gives a basic implementation
 * of the main methods.
 */
public abstract class AbstractGameObject implements GameObject {

    private Point2D position;
    private final EventSource<GameObject> destroyedEvent;

    /**
     * Initializes default data for this game object.
     */
    public AbstractGameObject() {
        this.position = Points2D.ZERO;
        this.destroyedEvent = new EventSource<>();
    }

    /**
     * Calls start() on each component.
     */
    @Override
    public void start() {
       this.getAllComponents().forEach(Component::start);
    }

    /**
     * Calls update(deltaTime) on each component.
     */
    @Override
    public void update(final double deltaTime) {
        this.getAllComponents().filter(Component::isEnabled).forEach(comp -> comp.update(deltaTime));
    }

    /**
     * Remove the gameObject from current level and triggers destroyedEvent.
     */
    @Override
    public void destroy() {
        LevelManager.getCurrentLevel().removeGameObject(this);
        this.destroyedEvent.trigger(this);
    }

    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    @Override
    public final Event<GameObject> getDestroyedEvent() {
        return this.destroyedEvent;
    }

}
