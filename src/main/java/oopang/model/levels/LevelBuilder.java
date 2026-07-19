package oopang.model.levels;

import java.util.function.Supplier;

import oopang.commons.PlayerTag;
import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.input.InputReader;
import oopang.model.powers.Power;

/**
 * The interface used to build objects of the type Level.
 */
public interface LevelBuilder {

    /**
     * Method that set the time of the level.
     * @param time
     *      the time used to set.
     * @return
     *      this LevelBuilder.
     */
    LevelBuilder setTime(double time);

    /**
     * Method that set the survival level.
     * @return
     *      this LevelBuilder. 
     */
    LevelBuilder setSurvival();

    /**
     * Method that add the ball into the level.
     * @param size
     *      the size of the object ball.
     * @param velocity
     *      the velocity of the object ball.
     * @param color
     *      the color of the object ball.
     * @param pos
     *      the position of the object ball.
     * @return
     *      this LevelBuilder.
     */
    LevelBuilder addBall(int size, Vector2D velocity, BallColor color, Point2D pos);

    /**
     * Method that add the wall into the level.
     * @param width
     *      the width of the wall.
     * @param height
     *      the height of the wall.
     * @param pos
     *      the position of the wall.
     * @return
     *      this LevelBuilder. 
     */
    LevelBuilder addWall(double width, double height, Point2D pos);

    /**
     * Method that adds a new AvailablePower in the level.
     * @param powerSupplier
     *      the powerSupplier of the level.
     * @return
     *      this LevelBuilder. 
     */
    LevelBuilder addAvailablePower(Supplier<Power> powerSupplier);

    /**
     * Method that set the BallGravity into the level.
     * @param gravity
     *      the gravity of the ball.
     * @return
     *      this LevelBuilder. 
     */
    LevelBuilder setBallGravity(Vector2D gravity);

    /**
     * Method that add the player into the level.
     * @param input
     *      the InputReader of the player.
     * @param playerTag
     *      the PlayerTag of the player.
     * @return
     *      this LevelBuilder. 
     */
    LevelBuilder addPlayer(InputReader input, PlayerTag playerTag);

    /**
     * Method that build the Level.
     * @return
     *      the Level built.
     */
    Level build();

}
