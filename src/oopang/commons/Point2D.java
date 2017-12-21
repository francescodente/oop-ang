package oopang.commons;

public interface Point2D {
	
    /**
     * Translate this Point by the given Vector
     * @param vector
     *      the Vector that represent the translation
     * @return
     *      new Point with its new value
     */     
    Point2D translate(final Vector2D vector);
    
    /**
     * Set X to the given value
     * @param value
     *      the value to be set as new X
     * @return
     *      new Point with the new Y value
     */
    Point2D setPointX(final double value);
    
    /**
     * Set Y to the given value
     * @param value
     *      the value to be set as new Y
     * @return
     *      new Point with the new Y value
     */
    Point2D setPointY(final double value);
    
    /**
     * Returns the X value of the Vector
     * @return
     *      X value
     */
    double getX();
    
    /**
     * Returns the Y value of the Vector
     * @return
     *      Y value 
     */
    double getY();

}
