package oopang.model.powers;
/**
 * This class represents all the enhancements that have temporary effects.
 *
 */
public abstract class PowerTimed extends AbstractPower {
    private double time;
    private final double timeout;
    /**
     * This constructor set time.
     * @param timeout
     *      Is the duration of enhancements.
     */
    public PowerTimed(final double timeout) {
       this.time = 0;
       this.timeout = timeout;
    }
    /**
     * It must be called in extended methods.
     */
    @Override
    public void update(final double deltaTime) {
        this.time += deltaTime;
        if (this.time > this.timeout) {
            this.deactivate();
        }
    }
}
