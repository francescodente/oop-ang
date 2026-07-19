package oopang.view.rendering;

/**
 * This class is a wrapper for layer constants for each renderer type.
 */
public final class Layers {

    /**
     * The layer for the Wall GameObject.
     */
    public static final int WALL_LAYER = 1;

    /**
     * The layer for the Shot GameObject.
     */
    public static final int SHOT_LAYER = 2;

    /**
     * The layer for the Player GameObject.
     */
    public static final int PLAYER_LAYER = 3;

    /**
     * The layer for the Ball GameObject.
     */
    public static final int BALL_LAYER = 4;

    /**
     * The layer for the Pickup GameObject.
     */
    public static final int PICKUP_LAYER = 5;

    /**
     * The layer for the Freeze power effect.
     */
    public static final int FROZEN_FRAME_LAYER = Integer.MAX_VALUE;

    /**
     * The layer for the TimedShield power effect.
     */
    public static final int SHIELD_LAYER = PLAYER_LAYER + 1;

    /**
     * The layer for the DoubleSpeed power effect.
     */
    public static final int AURA_LAYER = PLAYER_LAYER - 1;


    private Layers() {
        //Can not create a Layer object
    }

}
