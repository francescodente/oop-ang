package oopang.model.powers;

import java.util.function.Function;

/**
 * Factory for power that includes the logic of upgrades.
 */
public class UpgradePowerFactory implements PowerFactory {

    private final Function<PowerTag, Integer> powerToLevel;

    /**
     * Create a new factory.
     * @param levelmap
     *      The map that contains the level for each power.
     */
    public UpgradePowerFactory(final Function<PowerTag, Integer> powerToLevel) {
       this.powerToLevel = powerToLevel;
    }

    @Override
    public final Power createFreeze() {
        return Freeze.create(this.powerToLevel.apply(PowerTag.FREEZE));
    }

    @Override
    public final Power createTimedShield() {
        return TimedShield.create(this.powerToLevel.apply(PowerTag.TIMEDSHIELD));
    }

    @Override
    public final Power createDoubleSpeed() {
        return DoubleSpeed.create(this.powerToLevel.apply(PowerTag.DOUBLESPEED));
    }

    @Override
    public final Power createAdhesiveShot() {
        return StickyShotPower.create(this.powerToLevel.apply(PowerTag.STICKYSHOT));
    }

    @Override
    public final Power createDoubleShot() {
        return DoubleShot.create(this.powerToLevel.apply(PowerTag.DOUBLESHOT));
    }

    @Override
    public final Power createDynamite() {
        return Dynamite.create(this.powerToLevel.apply(PowerTag.DYNAMITE));
    }

}
