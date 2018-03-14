package oopang.view.rendering;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;

/**
 * Represents an image that can be rendered on the screen at a certain position on the screen
 * and with some rotation. The position specifies the pixel at which the pivot of the sprite will be positioned
 * relative to the bottom left corner of the canvas. The pivot is the point relative to the center of the image that
 * is used as the center for positioning. It can be set as a vector starting from the center of the image. Coordinates
 * in the range [-1,1] indicate a pivot inside the rectangle of the image. The image source is specified as an ImageID. 
 */
public interface Sprite extends Renderer {
    /**
     * Sets the position of the sprite in pixel coordinates an relative to the pivot.
     * @param pos
     *      the new position.
     */
    void setPosition(Point2D pos);

    /**
     * Returns the position of the sprite in pixel coordinates an relative to the pivot.
     * @return
     *      the position.
     */
    Point2D getPosition();

    /**
     * Sets the pivot of the sprite. The pivot is the point relative to the center of the image that
     * is used as the center for positioning. Coordinates in the range [-1,1] indicate a pivot inside
     * the rectangle of the image.
     * @param pivot
     *      the new pivot.
     */
    void setPivot(Vector2D pivot);

    /**
     * Returns the pivot of the sprite. The pivot is the point relative to the center of the image that
     * is used as the center for positioning. Coordinates in the range [-1,1] indicate a pivot inside
     * the rectangle of the image.
     * @return
     *      the pivot.
     */
    Vector2D getPivot();

    /**
     * Sets the width on the screen of the Sprite.
     * @param width
     *      the new width.
     */
    void setWidth(double width);

    /**
     * Sets the height on the screen of the Sprite.
     * @param height
     *      the new height.
     */
    void setHeight(double height);

    /**
     * Returns the width of the rectangle on the canvas.
     * @return
     *      the width in pixels.
     */
    double getWidth();

    /**
     * Returns the height of the rectangle on the canvas.
     * @return
     *      the height in pixels.
     */
    double getHeight();

    /**
     * Sets the portion of the source image to be rendered on screen.
     * @param topLeft
     *      The position of the top-left corner of the source rect.
     * @param offset
     *      The offset of the bottom-right corner from the top-left corner.
     */
    void setSourceWindow(Point2D topLeft, Vector2D offset);

    /**
     * Returns the width of the source image in pixels.
     * @return
     *      the width of the source image in pixels.
     */
    double getSourceWidth();

    /**
     * Returns the height of the source image in pixels.
     * @return
     *      the height of the source image in pixels.
     */
    double getSourceHeight();

    /**
     * Sets the source image of the sprite. This method resets the source window properties (i.e. the source rectangle).
     * @param sourceID
     *      the new source image identifier.
     */
    void setSource(ImageID sourceID);
}
