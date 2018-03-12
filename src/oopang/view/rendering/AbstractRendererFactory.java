package oopang.view.rendering;

import oopang.model.gameobjects.GameObject;

/**
 * Provides a base implementation of the {@link RendererFactory} interface.
 */
public abstract class AbstractRendererFactory implements RendererFactory {

    @Override
    public final Renderer createGameObjectRenderer(final GameObject obj) {
        // TODO Check to see what type of game object is given. Depending on that generate a new
        // appropriate GameObjectRenderer.
        return null;
    }
}
