package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.physics.CollisionTag;

/**
 * Represents a wall in the world.
 */
public class Wall extends AbstractGameObject {

    private final Component collision;
    private final double width;
    private final double height;

    /**
     * Creates a new Wall {@link GameObject} given its dimensions.
     * @param width
     *      the width of the wall.
     * @param height
     *      the height of the wall.
     */
    public Wall(final double width, final double height) {
        super();
        this.width = width;
        this.height = height;
        final Convex shape = new Rectangle(width, height);
        this.collision = new CollisionComponent(this, shape, CollisionTag.WALL);
    }

    @Override
    public Stream<Component> getAllComponents() {
        return Stream.of(this.collision);
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }
}
