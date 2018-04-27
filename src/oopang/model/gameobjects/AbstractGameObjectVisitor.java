package oopang.model.gameobjects;
/**
 * Provides a default implementation of the {@link GameObjectVisitor} interface that always
 * returns the default value.
 * @param <T>
 *      The return type.
 */
public abstract class AbstractGameObjectVisitor<T> implements GameObjectVisitor<T> {

    private final T defaultValue;
    /**
     * Initialize visitor to default value.
     * @param defaultValue
     *      The default value.
     */
    public AbstractGameObjectVisitor(final T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * The standard implementation is an empty visit which returns defaultValue.
     */
    @Override
    public T visit(final Player player) {
        return defaultValue;
    }

    /**
     * The standard implementation is an empty visit which returns defaultValue.
     */
    @Override
    public T visit(final Ball ball) {
        return defaultValue;
    }

    /**
     * The standard implementation is an empty visit which returns defaultValue.
     */
    @Override
    public T visit(final Wall wall) {
        return defaultValue;
    }

    /**
     * The standard implementation is an empty visit which returns defaultValue.
     */
    @Override
    public T visit(final HookShot shot) {
        return defaultValue;
    }

    /**
     * The standard implementation is an empty visit which returns defaultValue.
     */
    @Override
    public T visit(final Pickup pickup) {
        return defaultValue;
    }

}
