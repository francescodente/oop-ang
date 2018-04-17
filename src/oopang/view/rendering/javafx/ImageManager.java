package oopang.view.rendering.javafx;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import javafx.scene.image.Image;
import oopang.view.rendering.ImageID;

/**
 * Represents an object responsible for loading image from the disk when needed and make
 * other classes access them via their identifier.
 */
public class ImageManager {

    private static final ImageManager SINGLETON = new ImageManagerProxy();

    /**
     * Returns the single instance of the ImageManager.
     * @return
     *      the instance.
     */
    public static ImageManager getManager() {
        return SINGLETON;
    }

    /**
     * Returns the image corresponding to the given {@link ImageID}.
     * @param id
     *      The {@link ImageID} of the image to load.
     * @return
     *      The real {@link Image} object.
     */
    public Image getImage(final ImageID id) {
        return new Image(ImageManager.class.getResourceAsStream(id.getPath()));
    }

    /**
     * Loads all images except backgrounds.
     */
    public void loadAll() {
        Arrays.stream(ImageID.values())
        .filter(id -> !id.isBackground())
        .forEach(id -> this.getImage(id));
    }

    private static class ImageManagerProxy extends ImageManager {
        private final Map<ImageID, Image> imageMap;

        ImageManagerProxy() {
            super();
            this.imageMap = new EnumMap<>(ImageID.class);
        }

        @Override
        public Image getImage(final ImageID id) {
            if (!this.imageMap.containsKey(id)) {
                final Image img = super.getImage(id);
                this.imageMap.put(id, img);
                return img;
            } else {
                return this.imageMap.get(id);
            }
        }
    }
}
