package oopang.model.powers;

/**
 * This interface describes a factory for powers.
 */
public interface PowerFactory {
    /**
     * Create a new FreezePower.
     * @return
     *      Freeze Power
     */
    Power createFreeze();
    /**
     * Create a new TimedShield.
     * @return
     *      TimedShield.
     */
    Power createTimedShield();
    /**
     * Create a new DoubleSpeed.
     * @return
     *      DoubleSpeed
     */
    Power createDoubleSpeed();
    /**
     * Create a new AdhesiveShot.
     * @return
     *      AdhesiveShot.
     */
    Power createAdhesiveShot();
   /**
    * Create a new DoubleShot.
    * @return
    *       DoubleShot.
    */
    Power createDoubleShot();
    /**
     * Create a new Dynamite.
     * @return
     *      Dynamite.
     */
    Power createDynamite();
}
