package oopang.view.rendering.javafx;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
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

    public Parent createHeartIcon() {
        final VBox box = new VBox();
        final ImageView imageview = new ImageView(ImageID.HEART.getPath());
        box.getChildren().add(imageview);
        return box;
    }

    public Parent createTimedIcon(TimedPower power) {
        final VBox box = new VBox();
        final ImageView imageview = new ImageView();
        final Image image = new Image(ImageID.PICKUP.getPath());
        final double cellwidth = image.getWidth() / 3;
        final double cellheight = image.getHeight() / 2;
        switch (power.getPowertag()) {
        case DOUBLESPEED: imageview.setViewport(new Rectangle2D(cellwidth * 2, cellheight, cellwidth, cellheight)); break;
        case FREEZE: imageview.setViewport(new Rectangle2D(cellwidth * 2, 0, cellwidth, cellheight)); break;
        case TIMEDSHIELD: imageview.setViewport(new Rectangle2D(cellwidth, 0, cellwidth, cellheight)); break;
        default: break;
        }
        final ProgressBar bar = new ProgressBar(power.getRemainingTimePercentage());
        imageview.setImage(image);
        box.getChildren().add(imageview);
        box.getChildren().add(bar);
        return box;
    }

    public Parent createLevelTimeBar(Timeable timeable) {
        return new ProgressBar(timeable.getRemainingTimePercentage());
    }

    public Parent createShooterIcon(PowerTag tag) {
        final ImageView imageview = new ImageView();
        final Image image = new Image(ImageID.PICKUP.getPath());
        final double cellwidth = image.getWidth() / 3;
        final double cellheight = image.getHeight() / 2;
        switch (tag) {
        case DOUBLESHOT: imageview.setViewport(new Rectangle2D(0, 0, cellwidth, cellheight)); break;
        case ADHESIVESHOT: imageview.setViewport(new Rectangle2D(0, cellheight, cellwidth, cellheight)); break;
        default: break;
        }
        final VBox box = new VBox();
        imageview.setImage(image);
        box.getChildren().add(imageview);
        box.setVisible(true);
        return box;
    }
}
