package oopang.view.rendering.javafx;

import javafx.scene.image.Image;
import oopang.view.rendering.ImageID;

/**
 * Represents the object responsible of 
 */
public final class RealImageLoader extends ImageLoader {

    @Override
    public Image getImage(final ImageID id) {
        return new Image(ImageLoader.class.getResourceAsStream(id.getPath()));
    }
}
