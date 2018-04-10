package oopang.model.gameobjects;
/**
 * Provides a default implementation of the {@link GameObjectVisitor} interface that always
 * returns the default value.
 * @param <T>
 *      The return type.
 */
public class AbstractGameObjectVisitor<T> implements GameObjectVisitor<T> {

    private final T defaultValue;
    /**
     * Initialize visitor to default value.
     * @param defaultValue
     *      The default value.
     */
    public AbstractGameObjectVisitor(final T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public T visit(final Player player) {
        return defaultValue;
    }

    @Override
    public T visit(final Ball ball) {
        return defaultValue;
    }

    @Override
    public T visit(final Wall wall) {
        return defaultValue;
    }

    @Override
    public T visit(final HookShot shot) {
        return defaultValue;
    }

    @Override
    public T visit(final Pickup pickup) {
        return defaultValue;
    }

}
