package oopang.model.powers;

import oopang.commons.Timeable;
import oopang.commons.events.Event;
import oopang.commons.events.EventSource;

/**
 * This class represents all the enhancements that have temporary effects.
 *
 */
public abstract class TimedPower extends AbstractPower implements Timeable {
    private double time;
    private final double timeout;
    private final EventSource<Void> timeoutEvent;
    private final EventSource<Double> timeChangedEvent;
    /**
     * This constructor set time.
     * @param timeout 
     *      Is the duration of enhancements.
     * @param powertag 
     *      The PowerTag.
     */
    public TimedPower(final double timeout, final PowerTag powertag) {
       super(powertag);
       this.time = 0;
       this.timeout = timeout;
       this.timeoutEvent = new EventSource<>();
       this.timeChangedEvent = new EventSource<>();
    }
    /**
     * It must be called in extended methods.
     */
    @Override
    public void update(final double deltaTime) {
       if (this.isActive()) {
           this.time += deltaTime;
           this.timeChangedEvent.trigger(this.getRemainingTimePercentage());
       }
        if (this.time > this.timeout) {
            this.deactivate();
            this.timeoutEvent.trigger(null);
        }
    }

    @Override
    public double getRemainingTimePercentage() {
        return (this.timeout - this.time) / this.timeout;
    }

    @Override
    public Event<Void> getTimeOutEvent() {
        return this.timeoutEvent;
    }
    @Override
    public Event<Double> getTimeChangedEvent() {
        return this.timeChangedEvent;
    }
}



