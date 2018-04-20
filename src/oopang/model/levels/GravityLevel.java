package oopang.model.levels;

import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.components.GravityComponent;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.GameObjectFactory;
import oopang.model.gameobjects.GameObjectFactoryDecorator;

/**
 * Describes a level where gravity is different for all balls.
 */
public class GravityLevel extends LevelDecorator {

    private final Vector2D gravity;

    /**
     * Creates a new gravity level based on the given level.
     * @param baseLevel
     *      the {@link Level} to decorate.
     * @param gravity
     *      the gravity acceleration for the balls.
     */
    public GravityLevel(final Level baseLevel, final Vector2D gravity) {
        super(baseLevel);
        this.gravity = gravity;
    }

    @Override
    public GameObjectFactory getGameObjectFactory() {
        return new GameObjectFactoryDecorator(super.getGameObjectFactory()) {
            @Override
            public GameObject createBall(final int size, final Vector2D velocity, final BallColor color) {
                final GameObject ball = super.createBall(size, velocity, color);
                ball.getComponent(GravityComponent.class).get().setGravity(gravity);
                return ball;
            }
        };
    }
}
