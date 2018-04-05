package oopang.model.levels;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import oopang.commons.space.Point2D;
import oopang.commons.space.Vector2D;
import oopang.controller.PlayerTag;
import oopang.model.BallColor;
import oopang.model.input.InputReader;
import oopang.model.powers.Power;

/**
 * 
 */
public final class LazyLevelBuilder implements LevelBuilder {

    private boolean built;
    private double time;
    private Map<PlayerTag, InputReader> playerInputs;
    private List<Consumer<Level>> gameObjects;
    private List<Supplier<Power>> powers;
    private Optional<Vector2D> gravity;

    /**
     * 
     */
    public LazyLevelBuilder() {
        this.gameObjects = new LinkedList<>();
        this.powers = new LinkedList<>();
        this.gravity = Optional.empty();
        this.playerInputs = new EnumMap<>(PlayerTag.class);
        this.built = false;
    }

    @Override
    public LevelBuilder setTime(final double time) {
        this.time = time;
        return this;
    }

    @Override
    public LevelBuilder setSurvival() {
        this.time = -1;
        return this;
    }

    @Override
    public LevelBuilder addBall(final int size, final Vector2D velocity, final BallColor color, final Point2D pos) {
        this.gameObjects.add(l -> l.getGameObjectFactory()
                .createBall(size, velocity, color)
                .setPosition(pos));
        return this;
    }

    @Override
    public LevelBuilder addWall(final double width, final double height, final Point2D pos) {
        this.gameObjects.add(l -> l.getGameObjectFactory()
                .createWall(width, height)
                .setPosition(pos));
        return this;
    }

    @Override
    public LevelBuilder addAvailablePower(final Supplier<Power> powerSupplier) {
        this.powers.add(powerSupplier);
        return this;
    }

    @Override
    public LevelBuilder setBallGravity(final Vector2D gravity) {
        this.gravity = Optional.of(gravity);
        return this;
    }

    @Override
    public LevelBuilder addPlayer(final InputReader input, final PlayerTag playerTag) {
        this.playerInputs.put(playerTag, input);
        return this;
    }

    @Override
    public Level build() {
        if (this.built) {
            throw new IllegalStateException("Object already built");
        }
        Level level = new BaseLevel();
        if (this.time < 0) {
            level = new InfiniteLevel(level);
        } else if (this.time > 0) {
            level = new TimedLevel(level, this.time);
        }
        if (this.gravity.isPresent()) {
            //level = new ..
        }
        if (!this.powers.isEmpty()) {
            level = new PickUpGeneratingLevel(level, powers);
        }
        for (final Map.Entry<PlayerTag, InputReader> entry : this.playerInputs.entrySet()) {
            level = new SinglePlayerLevel(level, entry.getValue());
        }
        final Level finalLevel = level;
        this.gameObjects.stream().forEach(g -> g.accept(finalLevel));
        this.built = true;
        return finalLevel;
    }

}
