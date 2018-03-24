package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import oopang.commons.LevelManager;
import oopang.commons.space.Vectors2D;
import oopang.model.components.InputComponent;
import oopang.model.components.MovementComponent;
import oopang.model.gameobjects.GameObject;
import oopang.model.gameobjects.Player;
import oopang.model.gameobjects.Shot;
import oopang.model.input.InputController;
import oopang.model.input.InputDirection;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.Level;

/**
 * JUnit tests for {@link InputReader} and {@link InputWriter} interfaces.
 */
public class TestInput {

    private static final double DT = 0.1;

    /**
     * Test if the input direction works properly.
     */
    @Test
    public void testMovementInput() {
        final Player player = new Player();
        player.start();
        final MovementComponent movement = player.getComponent(MovementComponent.class).get();
        final InputController input = new InputController();
        player.getComponent(InputComponent.class).get().setInputReader(input);
        input.setDirection(InputDirection.NONE);
        player.update(DT);
        assertEquals(movement.getVelocity(), Vectors2D.ZERO);
        input.setDirection(InputDirection.LEFT);
        player.update(DT); // Test if input persists between frames.
        player.update(DT);
        player.update(DT);
        assertEquals(movement.getVelocity(), Vectors2D.LEFT.multiply(player.getSpeed()));
        input.setDirection(InputDirection.RIGHT);
        player.update(DT);
        assertEquals(movement.getVelocity(), Vectors2D.RIGHT.multiply(player.getSpeed()));
        input.setDirection(InputDirection.NONE);
        player.update(DT);
        assertEquals(movement.getVelocity(), Vectors2D.ZERO);
    }

    /**
     * Test if the shoot input works properly.
     */
    @Test
    public void testShootInput() {
        final List<GameObject> shootObjects = new ArrayList<>();
        final Level level = new BaseLevel();
        LevelManager.setCurrentLevel(level);
        final GameObject player = level.getGameObjectFactory().createPlayer();
        final InputController input = new InputController();
        player.getComponent(InputComponent.class).get().setInputReader(input);
        level.registerObjectCreatedEvent(o -> {
            if (o instanceof Shot) {
                shootObjects.add(o);
            }
        });
        level.start();
        input.setShooting(false);
        level.update(DT);
        assertEquals(shootObjects.size(), 0);
        input.setShooting(true);
        level.update(DT); // In this update the player reads the input.
        level.update(DT); // In this update the shot is started and the event is triggered.
        assertFalse(shootObjects.isEmpty());
    }
}
