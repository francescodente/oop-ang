package oopang.model.levels;

import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.controller.PlayerTag;
import oopang.model.BallColor;
import oopang.model.input.InputReader;
import oopang.model.powers.Power;

/**
 * 
 */
public interface LevelBuilder {

    /**
     * 
     * @param time
     * @return
     */
    LevelBuilder setTime(double time);

    /**
     * 
     * @return
     */
    LevelBuilder setSurvival();

    /**
     * 
     * @param size
     * @param velocity
     * @param color
     * @param pos
     * @return
     */
    LevelBuilder addBall(int size, Vector2D velocity, BallColor color, Point2D pos);

    /**
     * 
     * @param width
     * @param height
     * @param pos
     * @return
     */
    LevelBuilder addWall(double width, double height, Point2D pos);

    /**
     * 
     * @param powerSupplier
     * @return
     */
    LevelBuilder addAvailablePower(Supplier<Power> powerSupplier);

    /**
     * 
     * @param gravity
     * @return
     */
    LevelBuilder setBallGravity(Vector2D gravity);

    /**
     * 
     * @param input
     * @param playerTag
     * @return
     */
    LevelBuilder addPlayer(InputReader input, PlayerTag playerTag);

    /**
     * 
     * @return
     */
    Level build();

}
