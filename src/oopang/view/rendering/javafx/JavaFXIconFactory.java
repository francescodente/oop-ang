package oopang.view.rendering.javafx;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import oopang.commons.Timeable;
import oopang.model.powers.PowerTag;
import oopang.model.powers.TimedPower;
import oopang.view.rendering.ImageID;

/**
 * This is a factory that creates icon to be add to a javaFX scene.
 */
public class JavaFXIconFactory {

    private static final double HEART_SIZE = 40;

    /**
     * Create a icon for the heart.
     * @return
     *      the icon for the heart.
     */
    public ImageView createHeartIcon() {
        final ImageView image = new ImageView(ImageManager.getManager().getImage(ImageID.HEART));
        image.setFitWidth(HEART_SIZE);
        image.setFitHeight(HEART_SIZE);
        return image;
    }

    /**
     * Create a Timed icon.
     * @param power
     *      the power to update the icon
     * @return
     *      the timed icon for the given power
     */
    public VBox createTimedIcon(final TimedPower power) {
        final VBox box = new VBox();
        final ImageView imageview = this.createPowerIcon(power.getPowertag());
        final ProgressBar bar = this.createTimeBar(power);
        box.getChildren().add(imageview);
        box.getChildren().add(bar);
        return box;
    }

    /**
     * Creates a time bar.
     * @param timeable
     *      The timeable to update the progress bar with.
     * @return
     *      a progressBar that updates with the given timeable.
     */
    public ProgressBar createTimeBar(final Timeable timeable) {
        final ProgressBar bar = new ProgressBar(timeable.getRemainingTimePercentage());
        timeable.getTimeChangedEvent().register(t -> Platform.runLater(() -> bar.setProgress(t)));
        return bar;
    }

    /**
     * 
     * @param tag
     *      the powerTag to select the icon
     * @return
     *      a icon for the given powerTag.
     */
    public ImageView createPowerIcon(final PowerTag tag) {
        final ImageView imageview = new ImageView();
        final Image image = ImageManager.getManager().getImage(ImageID.PICKUP);
        final double cellwidth = image.getWidth() / 3;
        final double cellheight = image.getHeight() / 2;
        switch (tag) {
        case DOUBLESPEED: imageview.setViewport(new Rectangle2D(cellwidth * 2, cellheight, cellwidth, cellheight)); break;
        case FREEZE: imageview.setViewport(new Rectangle2D(cellwidth * 2, 0, cellwidth, cellheight)); break;
        case TIMEDSHIELD: imageview.setViewport(new Rectangle2D(cellwidth, 0, cellwidth, cellheight)); break;
        case DOUBLESHOT: imageview.setViewport(new Rectangle2D(0, 0, cellwidth, cellheight)); break;
        case ADHESIVESHOT: imageview.setViewport(new Rectangle2D(0, cellheight, cellwidth, cellheight)); break;
        default: break;
        }
        imageview.setImage(image);
        return imageview;
    }
}
