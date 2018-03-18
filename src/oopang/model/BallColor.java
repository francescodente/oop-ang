package oopang.model;

import java.util.Random;

/**
 * Annum representing the color of the balls.
 */
public enum BallColor {

    /**
     * The color RED.
     */
    RED,
    /**
     * The color BLUE.
     */
    BLUE,
    /**
     * The color YELLOW.
     */
    YELLOW,
    /**
     * The color GREEN.
     */
    GREEN,
    /**
     * The color ORANGE.
     */
    ORANGE,
    /**
     * The color PURPLE.
     */
    PURPLE;

    /**
     * Returns a random color.
     * @return
     *      a random color.
     */
    public static BallColor randomColor() {
        final Random random = new Random();
        return BallColor.values()[random.nextInt(BallColor.values().length)];
    }
}
