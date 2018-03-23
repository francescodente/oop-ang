package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.components.CollisionComponent;
import oopang.model.components.GravityComponent;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.components.ShooterComponent;
import oopang.model.gameobjects.Ball;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.Pickup;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Wall;

/**
 * JUnit test for {@link GameObject}s.
 */
public class TestGameObjects {
    /**
     * Test if the player has the right components attached.
     */
    @Test
    public void testPlayerComponents() {
        final GameObject player = new Player();
        assertTrue(player.getComponent(MovementComponent.class).isPresent());
        assertTrue(player.getComponent(InputComponent.class).isPresent());
        assertTrue(player.getComponent(ShooterComponent.class).isPresent());
        assertTrue(player.getComponent(CollisionComponent.class).isPresent());
    }

    /**
     * Test if the ball has the right components attached.
     */
    @Test
    public void testBallComponents() {
        final GameObject ball = new Ball(5, Vectors2D.LEFT, BallColor.BLUE);
        assertTrue(ball.getComponent(MovementComponent.class).isPresent());
        assertTrue(ball.getComponent(GravityComponent.class).isPresent());
        assertTrue(ball.getComponent(CollisionComponent.class).isPresent());
    }

    /**
     * Test if the ball has the right components attached.
     */
    @Test
    public void testWallComponents() {
        final GameObject wall = new Wall(1, 1);
        assertTrue(wall.getComponent(CollisionComponent.class).isPresent());
        assertFalse(wall.getComponent(GravityComponent.class).isPresent());
    }

    /**
     * Test if the ball has the right components attached.
     */
    @Test
    public void testPickUpComponents() {
        final GameObject wall = new Pickup(null);
        assertTrue(wall.getComponent(CollisionComponent.class).isPresent());
        assertTrue(wall.getComponent(GravityComponent.class).isPresent());
        assertTrue(wall.getComponent(MovementComponent.class).isPresent());
    }
}
