package oopang.view.rendering;

import oopang.model.gameobjects.GameObject;

/**
 * A factory for objects that can be rendered by a specific {@link CanvasDrawer}.
 * Use {@link CanvasDrawer}.getRendererFactory() to get the correct instance.
 */
public interface RendererFactory {
    /**
     * Creates a new Renderer that is responsible for drawing the given GameObject.
     * The type of renderer may be different depending on the type of GameObject.
     * @param obj
     *      the GameObject.
     * @return
     *      the Renderer for the given GameObject.
     */
    Renderer createGameObjectRenderer(GameObject obj);

    /**
     * Creates a new Sprite given its source image identifier.
     * @param sourceID
     *      the image identifier of this sprite.
     * @return
     *      the Sprite object.
     */
    Sprite createSprite(ImageID sourceID);

    /**
     * Creates a new renderer for the background of the game.
     * @return
     *      the renderer.
     */
    Renderer createBackgroundRenderer();
}
