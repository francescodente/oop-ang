package oopang.view.rendering.javafx;

import java.util.EnumMap;
import java.util.Map;

import javafx.scene.image.Image;
import oopang.view.rendering.ImageID;

/**
 * Represents a proxy for an image loader that adds caching functionality, keeping images
 * in memory after a load operation.
 */
public final class ImageLoaderCacheProxy extends ImageLoader {
    private final Map<ImageID, Image> imageMap;
    private final ImageLoader realLoader;

    /**
     * Creates a new cached image loader that uses the given loader to load the actual images.
     * @param realLoader
     *      the real loader object.
     */
    public ImageLoaderCacheProxy(final ImageLoader realLoader) {
        super();
        this.imageMap = new EnumMap<>(ImageID.class);
        this.realLoader = realLoader;
    }

    @Override
    public Image getImage(final ImageID id) {
        if (!this.imageMap.containsKey(id)) {
            final Image img = this.realLoader.getImage(id);
            this.imageMap.put(id, img);
            return img;
        } else {
            return this.imageMap.get(id);
        }
    }
}
