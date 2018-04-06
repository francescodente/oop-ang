package oopang.model.gameobjects;

import oopang.commons.LevelManager;
import oopang.commons.events.EventSource;
import oopang.commons.events.Event;
import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.model.components.Component;

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

    @Override
    public void start() {
       this.getAllComponents().forEach(Component::start);
    }

    @Override
    public void update(final double deltaTime) {
        this.getAllComponents().filter(Component::isEnabled).forEach(comp -> comp.update(deltaTime));
    }

    @Override
    public void destroy() {
        LevelManager.getCurrentLevel().removeGameObject(this);
        this.destroyedEvent.trigger(this);
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

    @Override
    public Event<GameObject> getDestroyedEvent() {
        return this.destroyedEvent;
    }

}
