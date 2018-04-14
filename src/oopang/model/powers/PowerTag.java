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

    private  final List<Integer> list;
    PowerTag(final List<Integer> list) {
        this.list = list;
    }
    /**
     * Getter for list.
     * @param x
     *      Index
     * @return 
     *      Index.
     */
    public int getCost(int x) {
        return list.get(x - 1);
    }
}
