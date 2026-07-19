package oopang.model;

import java.util.Arrays;
import java.util.Random;

/**
 * An enum representing the possible colors for the balls.
 * Can return a random Color.
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
        return Arrays.asList(BallColor.values()).get(random.nextInt(BallColor.values().length));
    }
}
