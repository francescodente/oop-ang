package oopang.model.gameobjects;

import java.util.stream.Stream;
import oopang.commons.space.Point2D;
import oopang.model.components.Component;

/**
 * This class represents the abstract model for all GameObjects and gives a basic implementation
 * of the main methods.
 */
public abstract class AbstractGameObject implements GameObject {

    private Point2D position;

    @Override
    public void start() {
       this.getAllComponents().forEach(Component::start);
    }

    @Override
    public void update(final double deltaTime) {
        this.getAllComponents().filter(Component::isEnabled).forEach(comp -> comp.update(deltaTime));
    }

    @Override
    public abstract void destroy();

    @Override
    public abstract Stream<Component> getAllComponents();

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }
}
