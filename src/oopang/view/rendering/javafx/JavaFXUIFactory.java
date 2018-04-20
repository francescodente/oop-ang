package oopang.view.rendering.javafx;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oopang.commons.Timeable;
import oopang.controller.users.User;
import oopang.model.powers.PowerTag;
import oopang.model.powers.TimedPower;
import oopang.view.dialogs.DialogFactory;
import oopang.view.dialogs.JavaFXDialogFactory;
import oopang.view.rendering.ImageID;

/**
 * This is a factory that creates icon to be add to a javaFX scene.
 */
public class JavaFXUIFactory {

    private static final double HEART_SIZE = 40;
    private static final double BAR_HEIGHT_PERCENTAGE = 0.3;
    private static final double PADDING_INTERNAL = 5;
    private static final double PADDING_EXTERNAL = 10;

    /**
     * Create a icon for the heart.
     * @return
     *      the icon for the heart.
     */
    public Node createHeartIcon() {
        final ImageView image = new ImageView(ImageLoader.getLoader().getImage(ImageID.HEART));
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
    public Node createTimedIcon(final TimedPower power) {
        final BorderPane pane = new BorderPane();
        pane.setMinHeight(0);
        pane.setMinWidth(0);
        final ImageView imageView = this.createPowerIcon(power.getPowertag());
        final ProgressBar bar = this.createTimeBar(power);
        pane.setCenter(imageView);
        pane.setBottom(bar);
        bar.setMinHeight(0);
        bar.prefHeightProperty().bind(pane.heightProperty().multiply(BAR_HEIGHT_PERCENTAGE));
        pane.prefWidthProperty().bind(pane.heightProperty().subtract(bar.heightProperty()));
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.widthProperty());
        return pane;
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
        bar.setMaxWidth(Double.MAX_VALUE);
        return bar;
    }

    /**
     * Creates an icon for the given power tag.
     * @param tag
     *      the powerTag to select the icon
     * @return
     *      a icon for the given powerTag.
     */
    public ImageView createPowerIcon(final PowerTag tag) {
        final ImageView imageview = new ImageView();
        final Image image = ImageLoader.getLoader().getImage(ImageID.PICKUP);
        final double cellwidth = image.getWidth() / 3;
        final double cellheight = image.getHeight() / 2;
        switch (tag) {
        case DOUBLESPEED: imageview.setViewport(new Rectangle2D(cellwidth * 2, cellheight, cellwidth, cellheight)); break;
        case FREEZE: imageview.setViewport(new Rectangle2D(cellwidth * 2, 0, cellwidth, cellheight)); break;
        case TIMEDSHIELD: imageview.setViewport(new Rectangle2D(cellwidth, 0, cellwidth, cellheight)); break;
        case DOUBLESHOT: imageview.setViewport(new Rectangle2D(0, 0, cellwidth, cellheight)); break;
        case ADHESIVESHOT: imageview.setViewport(new Rectangle2D(0, cellheight, cellwidth, cellheight)); break;
        case DYNAMITE: imageview.setViewport(new Rectangle2D(cellwidth, cellheight, cellwidth, cellheight)); break;
        default: break;
        }
        imageview.setImage(image);
        imageview.setPreserveRatio(true);
        return imageview;
    }


    /**
     * Create a node for the interface to upgrade a power.
     * @param user
     *      the active user
     * @param tag
     *      the powerTag to be displayed and upgraded
     * @return
     *      the interface to upgrade a power
     */
    public Node createUpdatePowerLevelBlock(final User user, final PowerTag tag) {
        final int powerLevel = user.getPowerLevels().get(tag);
        final Label levelLabel = new Label("Level " + powerLevel);
        final ImageView icon = this.createPowerIcon(tag);
        final ProgressBar bar = new ProgressBar(powerLevel / (double) tag.getMaxLevel());
        final String upgradeString = powerLevel == tag.getMaxLevel() ? "maxed" : tag.getCost(powerLevel) + " coins";
        final Button upgradeButton = new Button(upgradeString);
        upgradeButton.setId("upgradeButton");
        levelLabel.setId("levelLabel");
        bar.setId("powerLevelBar");
        if (powerLevel == tag.getMaxLevel()) {
            upgradeButton.setDisable(true);
        }
        final DialogFactory factory = new JavaFXDialogFactory();
        upgradeButton.setOnMouseClicked(e -> {
            if (user.spendCoins(tag.getCost(powerLevel))) {
                user.setPowerLevel(tag, powerLevel + 1);
            } else {
                factory.createNotEnoughCoins().show();
            }
        });
        final BorderPane mainPane = new BorderPane();
        mainPane.setLeft(icon);
        mainPane.setRight(upgradeButton);
        mainPane.setPadding(new Insets(PADDING_EXTERNAL));
        final VBox centerBox = new VBox(levelLabel, bar);
        centerBox.setPadding(new Insets(PADDING_INTERNAL));
        mainPane.setCenter(centerBox);
        return mainPane;
    }

}
