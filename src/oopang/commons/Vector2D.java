package oopang.commons;

public interface Vector2D {
    
    /**
     * Sum the given Vector to this Vector
     * @param vector
     *      the Vector to be summed
     * @return
     *      new Vector with its new value
     */
    Vector2D sumVector(final Vector2D vector);
    
    /**
     * Multiply this Vector by the given value
     * @param value 
     *       value to multiply the Vector with
     * @return
     *      new Vector with its new values
     */
    Vector2D multiply(final double value);
    
    /**
     * Set X to the given value
     * @param value
     *      the value to be set as new X
     * @return
     *      this Vector with its new values 
     */
    Vector2D setVectorX(final double value);
    
    /**
     * Change the sign of the X value
     * @return
     *      new Vector with its new values 
     */
    default Vector2D flipX() {
        return this.setVectorX(-this.getX());
    }
    
    /**
     * Set Y to the given value
     * @param value
     *      the value to be set as new Y
     * @return
     *      new Vector with its new values 
     */
    Vector2D setVectorY(final double value);
    
    /**
     * Change the sign of the Y value
     * @return
     *      new Vector with its new values 
     */
    default Vector2D flipY() {
        return this.setVectorY(-this.getY());
    }
    
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
    
    /**
     * Returns the module of the Vector
     * @return
     *      module 
     */
    double getModule();
    
    /**
     * Returns a new Vector with the same direction and module equal to 1.
     * @return
     *      the normalized version of this Vector
     */
    Vector2D normalized();
}
