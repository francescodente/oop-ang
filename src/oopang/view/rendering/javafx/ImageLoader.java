package oopang.view.rendering.javafx;

import java.util.Arrays;

import javafx.scene.image.Image;
import oopang.view.rendering.ImageID;

/**
 * Represents an object responsible of loading images from the disk when needed and make
 * other classes access them via their identifier.
 */
public abstract class ImageLoader {

    private static final ImageLoader SINGLETON = new ImageLoaderCacheProxy(new RealImageLoader());

    /**
     * Returns the single instance of the image loader.
     * @return
     *      the instance.
     */
    public static ImageLoader getLoader() {
        return SINGLETON;
    }

    /**
     * Returns the image corresponding to the given {@link ImageID}.
     * @param id
     *      The {@link ImageID} of the image to load.
     * @return
     *      The real {@link Image} object.
     */
    public abstract Image getImage(ImageID id);

    /**
     * Requests the loading of all images.
     */
    public void loadAll() {
        Arrays.stream(ImageID.values())
            .forEach(this::getImage);
    }
}
