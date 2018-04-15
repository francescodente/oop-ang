package oopang.model.powers;

import java.util.Arrays;
import java.util.List;

/**
 * Enum describing the power tag.
 *
 */
public enum PowerTag {
    /**
     * Tag AdhesiveShot.
     */
    ADHESIVESHOT(Arrays.asList(2000)),
    /**
     * Tag DoubleShot.
     */
    DOUBLESHOT(Arrays.asList(2000)),
    /**
     * Tag DoubleSpeed.
     */
    DOUBLESPEED(Arrays.asList(100, 200, 500, 1000)),
    /**
     * Tag TimedShield.
     */
    TIMEDSHIELD(Arrays.asList(100, 200, 500, 1000)),
    /**
     * Tag Dynamite.
     */
    DYNAMITE(Arrays.asList(500, 1000, 2000)),
    /**
     * Tag Freeze.
     */
    FREEZE(Arrays.asList(100, 200, 500, 1000));

    private final List<Integer> costs;

    PowerTag(final List<Integer> costs) {
        this.costs = costs;
    }
    /**
     * Getter for the cost.
     * @param level
     *      current level of the power
     * @return 
     *      the cost to upgrade to next level
     */
    public int getCost(final int level) {
        return costs.get(level - 1);
    }

    /**
     * Return the maxLevel of this power.
     * @return
     *      the max level
     */
    public int getMaxLevel() {
        return this.costs.size() +  1;
    }
}
