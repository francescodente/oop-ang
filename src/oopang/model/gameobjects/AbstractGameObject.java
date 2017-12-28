package oopang.model.gameobjects;

import java.util.stream.Stream;
import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.model.components.Component;
import oopang.model.levels.Level;

/**
 * This class represents the abstract model for all GameObjects and gives a basic implementation
 * of the main methods.
 */
public abstract class AbstractGameObject implements GameObject {

    private Point2D position;
    private final Level world;

    /**
     * Initializes default data for this game object.
     * @param level
     *      the level this game object belongs to.
     */
    public AbstractGameObject(final Level level) {
        this.world = level;
        this.position = Points2D.ZERO;
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
        this.world.removeGameObject(this);
    }

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
