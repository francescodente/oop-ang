package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.Model;
import oopang.model.World;
import oopang.model.gameobjects.GameObject;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.Level;

/**
 * JUnit test for {@link Level} and {@link GameObject}s management.
 */
public class TestLevel {

    private static final double DELTATIME = 0.1;
    private Level level;
    private Model model;


    /**
     * Setting up the test.
     */
    @Before
    public void setUp() {
        this.model = new World();
        this.level = new BaseLevel();
        this.model.setCurrentLevel(level);
    }

    /**
     * Test the addGameObject method.
     */
    @Test
    public void testAdd() {
        final GameObject ball = this.level.getGameObjectFactory().createBall(4, Vectors2D.LEFT, BallColor.BLUE);
        final GameObject wall = this.level.getGameObjectFactory().createWall(5, 5);
        assertFalse(this.level.getAllObjects().collect(Collectors.toList()).contains(ball));
        assertFalse(this.level.getAllObjects().collect(Collectors.toList()).contains(wall));
        this.level.update(DELTATIME);
        assertTrue(this.level.getAllObjects().collect(Collectors.toList()).contains(ball));
        assertTrue(this.level.getAllObjects().collect(Collectors.toList()).contains(wall));
    }

    /**
     * Test the removeGameObject method.
     */
    @Test
    public void testRemove() {
        final GameObject ball = this.level.getGameObjectFactory().createBall(4, Vectors2D.LEFT, BallColor.BLUE);
        final GameObject wall = this.level.getGameObjectFactory().createWall(5, 5);
        this.level.update(DELTATIME);
        ball.destroy();
        wall.destroy();
        this.level.update(DELTATIME);
        assertFalse(this.level.getAllObjects().collect(Collectors.toList()).contains(ball));
        assertFalse(this.level.getAllObjects().collect(Collectors.toList()).contains(wall));
    }
}
