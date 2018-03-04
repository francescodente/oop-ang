package oopang.model.gameobjects;

import java.util.stream.Stream;

import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import oopang.model.components.CollisionComponent;
import oopang.model.components.Component;
import oopang.model.components.GravityComponent;
import oopang.model.components.MovementComponent;
import oopang.model.physics.CollisionTag;
/**
 *  This class represents the GameObject Pick-up, which is a series of enhancements 
 *  that the player can get colliding.
 * 
 * @author NicholasCiarafoni
 */
public class Pickup extends AbstractGameObject {
    private double time;
    private static final double WIDTH = 5;
    private static final double HEIGHT = 5;
    private static final double TIMEOUT = 10000;
    private final GravityComponent gravitycomponent;
    private final MovementComponent movementcomponent;
    private final CollisionComponent collisioncomponent;
    /**
     * Create a Pick-Up GameObject
     * @param time
     * @param gravitycomponent
     * @param movementcomponent
     * @param collisioncomponent
     */
   public Pickup() {
        super();
        this.time = time;
        
        final Convex pickup = new Rectangle(WIDTH, HEIGHT);
        this.collisioncomponent = new CollisionComponent(this,pickup, CollisionTag.PICKUP);
    }
    //private final Power power;
    /*nota per me: completare un package power con un interfaccia Power,
     *  2 classi astratte rispettivamente 1 per i pickup timed, e 1 per i pickup instant
     *  pickup timed, sono pickup che raggiunto un tempo limite svaniscono e tornano alle 
     *  impostazioni iniziali, i pickup instant, sono i potenziamenti istantanei che vanno 
     *  a colpire il tempo e altro, fino alla durata del livello/vita del player.
  */
/**
 * This method using the deltaTime to update, the sum of deltaTimes are used to set Pick-up's
 * lifetime.
 */
    @Override
    public void update(final double deltaTime) {
        super.update(deltaTime);
        this.time += deltaTime;
        if (this.time > TIMEOUT) {
            destroy();
        }
    }

    @Override
    public Stream<Component> getAllComponents() {
        return Stream.of(this.gravitycomponent, this.movementcomponent, this.collisioncomponent); 
    }
}
