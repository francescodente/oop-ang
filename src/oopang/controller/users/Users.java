package oopang.controller.users;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import oopang.model.powers.PowerTag;

/**
 * 
 */
public final class Users {

    private static final int MAX_LEVEL = 10;
    private static final double LIMIT_MULTIPLIER = 1.8;
    private static final int MIN_XP_LIMIT = 100000;
    private static final List<Integer> LEVELS_REWARD = Arrays.asList(100, 100, 500, 500, 1500, 1500, 1500, 2000, 3000,
            3500);

    private Users() {
    }

    public static int computeRank(final User user) {
        int rank = 0;
        int xpPoints = user.getXpPoints();
        while (xpPoints > computeNextRankLimit(rank) && rank <= MAX_LEVEL) {
            xpPoints -= computeNextRankLimit(rank);
            rank++;
        }
        return rank;
    }

    public static int computeExceedingXpPoints(final User user) {
        return user.getXpPoints() - computeRankTotalPoints(computeRank(user));
    }

    public static double computeXpLevelPercentage(final User user) {
        return computeExceedingXpPoints(user) / (double) computeNextRankLimit(computeRank(user));
    }

    public static boolean upgradePower(final User user, final PowerTag power) {
        final int currentLevel = user.getPowerLevel(power);
        final int cost = power.getCost(currentLevel);
        if (user.getCoins() > cost) {
            user.changeCoins(-cost);
            user.setPowerLevel(power, currentLevel + 1);
            return true;
        }
        return false;
    }

    public static void addXpPoints(final User user, final int points) {
        final int rankBefore = computeRank(user);
        user.addXpPoints(points);
        final int rankAfter = computeRank(user);
        if (rankAfter > rankBefore) {
            user.changeCoins(rankAfter - 1);
        }
    }

    private static int computeRankTotalPoints(final int rank) {
        return Stream.iterate(0, i -> i + 1)
                .limit(rank)
                .mapToInt(Users::computeNextRankLimit)
                .sum();
    }

    private static int computeNextRankLimit(final int currentRank) {
        return MIN_XP_LIMIT * (int) Math.pow(LIMIT_MULTIPLIER, currentRank);
    }

    public static Map<PowerTag, Integer> defaultPowerLevels() {
        return Arrays.stream(PowerTag.values()).collect(Collectors.toMap(p -> p, p -> 1));
    }

    public static Map<UserStat, Integer> defaultUserStats() {
        return Arrays.stream(UserStat.values()).collect(Collectors.toMap(s -> s, s -> s.getDefaultValue()));
    }
}
