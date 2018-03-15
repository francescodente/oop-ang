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
    },
    /**
     * Collision tag for all ball.
     */
    BALL {
        @Override
        public boolean canCollideWith(final CollisionTag other) {
            return other == WALL || other == PLAYER || other == SHOT;
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
     * Returns true if object of this type can collide with objects of the given one.
     * @param other
     *      the other type.
     * @return
     *      true if the objects can collide.
     */
    public boolean canCollideWith(final CollisionTag other) {
        return true;
    }
}
