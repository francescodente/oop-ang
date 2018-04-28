package oopang.model.powers;

import java.util.Map;

/**
 * Factory for power that includes the logic of upgrades.
 */
public class UpgradePowerFactory implements PowerFactory {

    private final Map<PowerTag, Integer> levelmap;

    /**
     * Create a new factory.
     * @param levelmap
     *      The map that contains the level for each power.
     */
    public UpgradePowerFactory(final Map<PowerTag, Integer> levelmap) {
       this.levelmap = levelmap;
    }

    @Override
    public final Power createFreeze() {
        return Freeze.create(this.levelmap.get(PowerTag.FREEZE));
    }

    @Override
    public final Power createTimedShield() {
        return TimedShield.create(this.levelmap.get(PowerTag.TIMEDSHIELD));
    }

    @Override
    public final Power createDoubleSpeed() {
        return DoubleSpeed.create(this.levelmap.get(PowerTag.DOUBLESPEED));
    }

    @Override
    public final Power createAdhesiveShot() {
        return StickyShotPower.create(this.levelmap.get(PowerTag.STICKYSHOT));
    }

    @Override
    public final Power createDoubleShot() {
        return DoubleShot.create(this.levelmap.get(PowerTag.DOUBLESHOT));
    }

    @Override
    public final Power createDynamite() {
        return Dynamite.create(this.levelmap.get(PowerTag.DYNAMITE));
    }

}
