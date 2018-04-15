package oopang.view.rendering.powers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
public class UltraInstinctRenderer extends BlinkingPowerRenderer {

    private static final double H_OFFSET = 8;
    private static final double V_OFFSET = 5;
    private static final int LAST_POSITIONS_SAVED = 10;

    private final Queue<Point2D> lastPositions;
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
        this.fadingSprites = Stream.iterate(1, a -> a - 1 / LAST_POSITIONS_SAVED)
                .limit(LAST_POSITIONS_SAVED)
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
        this.lastPositions = new LinkedList<>();
        sprite.setSource(ImageID.SHIELD);
        sprite.setPivot(Vectors2D.of(0, -1));
        this.setLayer(Layers.AURA_LAYER);
        sprite.setWidth(player.getWidth() + H_OFFSET);
        sprite.setHeight(player.getHeight() + V_OFFSET);
        for (int i = 0; i < this.fadingSprites.size(); i++) {
            this.lastPositions.add(player.getPosition());
        }
    }

    @Override
    public void render() {
        this.getSprite().setPosition(this.getPlayer().getPosition());
        super.render();
    }

}
