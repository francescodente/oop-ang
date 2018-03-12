package oopang.model.gameobjects;

import oopang.commons.space.Vector2D;
import oopang.model.powers.Power;

/**
 * A factory for {@link GameObject}s that also adds them to the current level.
 */
public interface GameObjectFactory {
    /**
     * Creates a new player {@link GameObject} and adds it to the level.
     * @return
     *      the player {@link GameObject}.
     */
    GameObject createPlayer();

    /**
     * Creates a new bubble {@link GameObject} given its radius and adds it to the level.
     * @param radius
     *      the radius of the bubble.
     * @param vector
     *      the velocity of the bubble.
     * @return
     *      the bubble {@link GameObject}.
     */
    GameObject createBall(double radius, Vector2D vector);

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
