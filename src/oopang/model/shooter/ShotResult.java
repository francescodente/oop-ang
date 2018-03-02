package oopang.model.shooter;

import oopang.model.gameobjects.Shot;
import oopang.model.physics.CollisionTag;

/**
 * This enum describes the result of a Shot collision.
 *
 */
public class ShotResult {

    private final CollisionTag tag;
    private final Shot shot;

    /**
     * Create a new ShotResult object.
     * @param tag
     *      the collisiontag
     * @param shot
     *      the shot reference
     */
    public ShotResult(final CollisionTag tag, final Shot shot) {
        this.shot = shot;
        this.tag = tag;
    }

    /**
     * Return shotResult Tag.
     * @return
     *  the collision tag
     */
    public CollisionTag getTag() {
        return tag;
    }

    /**
     * Return shotResult shot reference.
     * @return
     *  the shot reference
     */
    public Shot getShot() {
        return shot;
    }

}
