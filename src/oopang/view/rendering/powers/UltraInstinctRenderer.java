package oopang.view.rendering.powers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import oopang.commons.PlayerTag;
import oopang.commons.space.Point2D;
import oopang.commons.space.Vectors2D;
import oopang.model.gameobjects.Player;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.CanvasDrawer;
import oopang.view.rendering.ImageID;
import oopang.view.rendering.Layers;
import oopang.view.rendering.Sprite;

/**
 * This class is a renderer that displays an aura around the player when the doublespeed pickup
 * is activated and add a fading trail to the player.
 *
 */
public final class UltraInstinctRenderer extends BlinkingTimeableRenderer {

    private static final double H_OFFSET = 8;
    private static final double V_OFFSET = 5;
    private static final int TRAIL_SPRITES = 5;
    private static final int FRAMES_SKIPPED = 7;

    private final List<Point2D> lastPositions;
    private final List<Sprite> fadingSprites;

    /** 
    * Creates a new Shield renderer.
    * @param sprite
    *      the sprite to be used in this renderer
    * @param player
    *      the player who picked up this power
    * @param power
    *      the power activated 
    * @param drawer
    *      the current canvasDrawer
    */
    public UltraInstinctRenderer(final Sprite sprite, final Player player, final TimedPower power, final CanvasDrawer drawer) {
        super(sprite, player, power, drawer);
        this.fadingSprites = Stream.iterate(0, i -> i + 1)
                .limit(TRAIL_SPRITES)
                .map(i -> i * 1.0 / TRAIL_SPRITES)
                .map(a -> {
                    final Sprite s = drawer.getRendererFactory().createSprite();
                    s.setAlpha(a);
                    s.setLayer(Layers.AURA_LAYER - 1);
                    s.setHeight(player.getHeight());
                    s.setWidth(player.getWidth());
                    s.setPivot(Vectors2D.of(0, -1));
                    if (player.getPlayerTag() == PlayerTag.PLAYER_ONE) {
                        s.setSource(ImageID.PLAYER1);
                    } else {
                        s.setSource(ImageID.PLAYER2);
                    }
                    return s;
                })
                .collect(Collectors.toList());
        this.lastPositions = Stream.generate(() -> player.getPosition())
                .limit(TRAIL_SPRITES * FRAMES_SKIPPED)
                .collect(Collectors.toList());
        sprite.setSource(ImageID.ULTRA_INSTINCT);
        sprite.setPivot(Vectors2D.of(0, -1));
        this.setLayer(Layers.AURA_LAYER);
        sprite.setWidth(player.getWidth() + H_OFFSET);
        sprite.setHeight(player.getHeight() + V_OFFSET);
        sprite.setAlpha(0.5);
        power.getTimeOutEvent().register(
                n -> this.fadingSprites.forEach(drawer::removeRenderer));
    }

    @Override
    public void render() {
        this.getSprite().setPosition(this.getPlayer().getPosition());
        Stream.iterate(0, i -> i + 1)
            .limit(this.fadingSprites.size())
            .forEach(i -> this.fadingSprites.get(i).setPosition(this.lastPositions.get(i * FRAMES_SKIPPED)));
        this.lastPositions.remove(0);
        this.lastPositions.add(this.getPlayer().getPosition());
        super.render();
    }

}
