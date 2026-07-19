package oopang.model.physics;

/**
 * Represents a value used to decide if an object type can collide with another.
 */
public enum CollisionTag {

    /**
     * Collision tag for the player object.
     */
    PLAYER {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == BALL || other == PICKUP;
        }

        @Override
        public boolean isStaticWith(final CollisionTag other) {
            return other != WALL;
        }
    },

    /**
     * Collision tag for all ball.
     */
    BALL {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == PLAYER || other == SHOT;
        }

        @Override
        public boolean isStaticWith(final CollisionTag other) {
            return other == PLAYER || other == SHOT;
        }
    },

    /**
     * Collision tag for all walls.
     */
    WALL {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other != WALL;
        }
    },

    /**
     * Collision tag for all pickups.
     */
    PICKUP {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == PLAYER;
        }

        @Override
        public boolean isStaticWith(final CollisionTag other) {
            return other != WALL;
        }
    },

    /**
     * Collision tag for all shots.
     */
    SHOT {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == BALL;
        }
    };

    /**
     * Returns true if objects of this type can collide with objects of the given one.
     * @param other
     *      the other type.
     * @return
     *      true if the objects can collide.
     */
    public boolean canCollideWith(final CollisionTag other) {
        return true;
    }

    /**
     * Returns false if the collision between this type and the given one should force this
     * object to be moved so that the two objects don't overlap. If the result is true this object
     * should not move.
     * @param other
     *      the other type.
     * @return
     *      a boolean indicating the type of collision solving method.
     */
    public boolean isStaticWith(final CollisionTag other) {
        return true;
    }
}
