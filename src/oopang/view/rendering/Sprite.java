package oopang.view.rendering;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;

/**
 * Represents an image that can be rendered on the screen at a certain position on the screen
 * and with some rotation. The position specifies the pixel at which the pivot of the sprite will be positioned
 * relative to the bottom left corner of the canvas. The pivot is the point relative to the center of the image that
 * is used as the center for rotations. It can be set as a vector starting from the center of the image. Coordinates
 * in the range [-1,1] indicate a pivot inside the rectangle of the image. The rotation is specified as the number
 * of radians to rotate the image counter-clockwise. The image source is specified as an ImageID. 
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
     * Sets the pivot of the sprite.
     * @param pivot
     *      the new pivot.
     */
    void setPivot(Vector2D pivot);

    /**
     * Returns the pivot of the sprite.
     * @return
     *      the pivot.
     */
    Vector2D getPivot();

    /**
     * Sets the rotation of the sprite around the pivot.
     * @param angle
     *      the new angle in radians.
     */
    void setAngle(double angle);

    /**
     * Returns the rotation of the sprite around the pivot.
     * @return
     *      the rotation.
     */
    double getAngle();

    /**
     * Sets the source image of the sprite.
     * @param sourceID
     *      the new source image identifier.
     */
    void setSource(ImageID sourceID);
}
