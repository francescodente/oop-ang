package oopang.model.powers;
/**
 * This class create the powers Factory.
 *
 */
public class BasicPowerFactory implements PowerFactory {
    private static final int STARTLEVEL = 1;
    @Override
    public final Power createFreeze() {
        return Freeze.create(STARTLEVEL);
    }

    @Override
    public final Power createTimedShield() {
        return TimedShield.create(STARTLEVEL);
    }

    @Override
    public final Power createDoubleSpeed() {
        return DoubleSpeed.create(STARTLEVEL);
    }

    @Override
    public final Power createAdhesiveShot() {
        return AdhesiveShot.create(STARTLEVEL);
    }

    @Override
    public final Power createDoubleShot() {
        return DoubleShot.create(STARTLEVEL);
    }

}
