package oopang.model.physics;

/**
 * Represents a value used to decide if an object type can collide with another.
 */
public enum CollisionTag {
    /**
     * Collision tag for the player object.
     */
    PLAYER,
    /**
     * Collision tag for all bubbles.
     */
    BUBBLE,
    /**
     * Collision tag for the floor.
     */
    FLOOR,
    /**
     * Collision tag for the ceiling.
     */
    CEILING,
    /**
     * Collision tag for all walls.
     */
    WALL,
    /**
     * Collision tag for all pickups.
     */
    PICKUP,
    /**
     * Collision tag for all shots.
     */
    SHOT;
}
