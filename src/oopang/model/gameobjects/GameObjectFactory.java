package oopang.model.gameobjects;

import oopang.commons.PlayerTag;
import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
import oopang.model.powers.Power;

/**
 * A factory for {@link GameObject}s that also adds them to the current {@link oopang.model.levels.Level}.
 */
public interface GameObjectFactory {

    /**
     * Creates a new player {@link GameObject} and adds it to the level.
     * @param tag
     *      the tag used to distinguish the two players
     * @return
     *      the player {@link GameObject}.
     */
    GameObject createPlayer(PlayerTag tag);

    /**
     * Creates a new ball {@link GameObject} given its radius and adds it to the level.
     * @param size
     *      the size of the ball.
     * @param velocity
     *      the velocity of the ball.
     * @param color
     *      the color of the ball.
     * @return
     *      the ball {@link GameObject}.
     */
    GameObject createBall(int size, Vector2D velocity, BallColor color);

    /**
     * Creates a new hook {@link Shot} and adds it to the level.
     * @return
     *      the hook {@link Shot}.
     */
    Shot createHookShot();

    /**
     * Create a new sticky {@link Shot} and adds it to the level.
     * @return
     *      the sticky {@link Shot}
     */
    Shot createStickyShot();

    /**
     * Creates a new wall {@link GameObject} and adds it to the level.
     * @param width
     *      the width of the wall.
     * @param height
     *      the height of the wall.
     * @return
     *      the wall {@link GameObject}.
     */
    GameObject createWall(double width, double height);

    /**
     * Creates a new pickup {@link GameObject} and adds it to the level.
     * @param power
     *      the power of the pickup
     * @return
     *      the pickup {@link GameObject}.
     */
    GameObject createPickup(Power power);
}
