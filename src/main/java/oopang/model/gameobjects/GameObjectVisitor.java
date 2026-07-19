package oopang.model.gameobjects;

/**
 * Describes a visitor for {@link GameObject}s that can perform type specific operations
 * for objects of type {@link Ball}, {@link Player}, {@link Shot}, {@link Pickup} and {@link Wall}.
 * @param <T>
 *      the type of the returning value for the visits.
 */
public interface GameObjectVisitor<T> {

    /**
     * Visit a {@link Player} object.
     * @param player
     *      the {@link Player}.
     * @return
     *      the result of the visit.
     */
    T visit(Player player);

    /**
     * Visit a {@link Ball} object.
     * @param ball
     *      the {@link Ball}.
     * @return
     *      the result of the visit.
     */
    T visit(Ball ball);

    /**
     * Visit a {@link Wall} object.
     * @param wall
     *      the {@link Wall}.
     * @return
     *      the result of the visit.
     */
    T visit(Wall wall);

    /**
     * Visit a {@link HookShot} object.
     * @param shot
     * the {@link HookShot}.
     * @return
     *      the result of the visit.
     */
    T visit(HookShot shot);

    /**
     * Visit a {@link Pickup} object.
     * @param pickup
     * the {@link Pickup}.
     * @return
     *      the result of the visit.
     */
    T visit(Pickup pickup);
}
