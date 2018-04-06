package oopang.model.powers;

import oopang.commons.Timeable;

/**
 * This class represents all the enhancements that have temporary effects.
 *
 */
public abstract class PowerTimed extends AbstractPower implements Timeable {
    private double time;
    private final double timeout;
    /**
     * This constructor set time.
     * @param timeout 
     *      Is the duration of enhancements.
     * @param powertag 
     *      The PowerTag.
     */
    public PowerTimed(final double timeout, final PowerTag powertag) {
       super(powertag);
       this.time = 0;
       this.timeout = timeout;
    }
    /**
     * It must be called in extended methods.
     */
    @Override
    public void update(final double deltaTime) {
       if (this.isActive()) {
           this.time += deltaTime;
       }
        if (this.time > this.timeout) {
            this.deactivate();
        }
    }

    @Override
    public double getRemainingTimePercentage() {
        return this.time / this.timeout;
    }
}



