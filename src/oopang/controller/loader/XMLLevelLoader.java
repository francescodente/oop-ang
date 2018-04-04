package oopang.controller.loader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.InfiniteLevel;
import oopang.model.levels.Level;
import oopang.model.levels.PickUpGeneratingLevel;
import oopang.model.levels.TimedLevel;
import oopang.model.powers.Power;
import oopang.model.powers.PowerFactory;
import oopang.model.powers.PowerTag;
import oopang.view.rendering.ImageID;

/**
 * Class that load the XMLFile into the level.
 */
public class XMLLevelLoader implements LevelLoader {

    private static final String PATH = "/levels/";

    private final PowerFactory powerFactory;

    /**
     * Constructor of the Class setting the base path.
     * @param factory
     *      The power Factory
     */
    public XMLLevelLoader(final PowerFactory factory) {
        this.powerFactory = factory;
    }

    @Override
    public LevelData loadInfiniteLevel() {
        return loadLevel(Optional.empty());
    }

    @Override
    public LevelData loadStoryLevel(final int index) {
        return loadLevel(Optional.of(index));
    }

    /**
     * Method that loads informations from XML file an decorate the appropriate {@link Level}. 
     * @param index
     *      the index of the {@link StoryModeGameSession}
     * @return
     *      A {@link LevelData} decorated
     */
    private LevelData loadLevel(final Optional<Integer> index) {
        final Level level = new BaseLevel();
        final String path;
        if (index.isPresent()) {
            path = PATH + "Level" + index.get() + ".xml";
        } else {
            path = PATH + "InfiniteLevel.xml";
        }
        try {
            final DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            final File file = new File(this.getClass().getResource(path).toURI());
            final DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            final ImageID background = this.loadLevelData(level, doc);
            final ImageID wallTexture = this.loadWallTexture(level, doc);
            this.getPowers(level, doc);
            this.loadBall(level, doc);
            this.loadWalls(level, doc);
            return new LevelData(background, wallTexture, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Utility method to get Powers form file and decorate the level.
     * @param level
     *      The level to decorate.
     * @param doc
     *      The Document to load
     */
    private void getPowers(Level level, final Document doc) {
        List<Supplier<Power>> powerList = new LinkedList<>();
        final NodeList powers = doc.getElementsByTagName("AvailablePower");
        final Node availablePowers = powers.item(0);
        final Element powerField = (Element) availablePowers;
        final int length = powerField.getElementsByTagName("Power").getLength();
        for (int i = 0; i < length; i++) {
            final Element power = (Element) powerField.getElementsByTagName("Power").item(i);
            PowerTag pow = PowerTag.valueOf(power.getAttribute("id"));
            if (pow == PowerTag.ADHESIVESHOT) {
                powerList.add(() -> powerFactory.createAdhesiveShot());
            }
            if (pow == PowerTag.DOUBLESHOT) {
                powerList.add(() -> powerFactory.createDoubleShot());
            }
            if (pow == PowerTag.DOUBLESPEED) {
                powerList.add(() -> powerFactory.createDoubleSpeed());
            }
            if (pow == PowerTag.FREEZE) {
                powerList.add(() -> powerFactory.createFreeze());
            }
            if (pow == PowerTag.TIMEDSHIELD) {
                powerList.add(() -> powerFactory.createTimedShield());
            }
        }
        level = new PickUpGeneratingLevel(level, powerList);
    }

    /**
     * Get the {@link Ball} attributes and use the {@link GameObjectFactory} to generate new balls.
     * @param level
     *      The reference of the {@link Level}
     * @param doc
     *      The document to load
     */
    private void loadBall(final Level level, final Document doc) {
        final NodeList balls = doc.getElementsByTagName("Ball");
        for (int k = 0; k < balls.getLength(); k++) {
            final Node ball = balls.item(k);
            final Element attr = (Element) ball;
            final BallColor color = BallColor.valueOf(attr.getAttribute("color"));
            final int size = Integer.parseInt(attr.getAttribute("size"));
            level.getGameObjectFactory().createBall(size, getVector(k, "Velocity", "Ball", doc), color).setPosition(this.getPoint(k, "Position", "Ball", doc));
        }
    }

    /**
     * Getter for {@link Vectors2D} field in a XML document.
     * @param doc
     *      The document containing the {@link Level}.
     * @param item
     *      The number of {@link Vectors2D} tag in the TreeNode
     * @param node
     *      The name of the TreeNode.
     * @param doc
     *      The document to load
     * @return
     */
    private Vector2D getVector(final int item, final String target, final String source, final Document doc) {
        final NodeList nList = doc.getElementsByTagName(source);
        final Node nNode = nList.item(item);
        final Element objectElement = (Element) nNode;
        final NodeList vector = objectElement.getElementsByTagName(target);
        final Node ballField = vector.item(0);
        final Element ballElement = (Element) ballField;
        final Element vectorElement = (Element) ballElement.getElementsByTagName("Vector").item(0);
        return Vectors2D.of(Double.parseDouble(vectorElement.getAttribute("x")), Double.parseDouble(vectorElement.getAttribute("y")));
    }

    /**
     * Get the {@link Point2D} of the {@link Ball} into the file.
     * @param doc
     *      The document where to search.
     * @param item
     *      The number of position to get.
     * @return
     *      The {@link Point2D} of the {@link Ball}
     */
    private Point2D getPoint(final int item, final String target, final String source, final Document doc) {
        final NodeList nList = doc.getElementsByTagName(source);
        final Node nNode = nList.item(item);
        final Element objectElement = (Element) nNode;
        final NodeList position = objectElement.getElementsByTagName(target);
        final Node positionField = position.item(0);
        final Element point = (Element) positionField;
        final Element pointElement = (Element) point.getElementsByTagName("Point").item(0);
        return Points2D.of(Double.valueOf(pointElement.getAttribute("x")), Double.valueOf(pointElement.getAttribute("y")));
    }

    /**
     * Load {@link ImageID} from file, create the {@link Level} and decorate it.
     * @param level
     *      Instance of {@link Level} to create
     * @param doc
     *      The document with informations.
     * @return
     *      The {@link ImageID} loaded.
     */
    private ImageID loadLevelData(Level level, final Document doc) {
        final NodeList nList = doc.getElementsByTagName("Level");
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        final ImageID background = ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
        if (eElement.getElementsByTagName("Time").getLength() > 0) {
            level = new TimedLevel(level, Double.parseDouble(eElement.getElementsByTagName("Time").item(0).getTextContent()));
        } else {
            level = new InfiniteLevel(level);
        }
        return background;
    }

    /**
     * Method to load the {@link ImageID} of the {@link Wall} in this {@link Level}.
     * @param level
     *      the {@link Level} to decorate
     * @param doc
     *      The XML File
     * @return
     *      The texture of the walls.
     */
    private ImageID loadWallTexture(final Level level, final Document doc) {
        final NodeList nList = doc.getElementsByTagName("Level");
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        return ImageID.valueOf(eElement.getElementsByTagName("WallTexture").item(0).getTextContent());
    }

    /**
     * Load {@link Wall} from the XML file.
     * @param level
     *      The instance of {@link Level} where create {@link Wall}
     */
    private void loadWalls(final Level level, final Document doc) {
        final NodeList wallList = doc.getElementsByTagName("Wall");
        for (int i = 0; i < wallList.getLength(); i++) {
            final Node wall = wallList.item(i);
            final Element attr = (Element) wall;
            final double height = Double.parseDouble(attr.getAttribute("height"));
            final double width = Double.parseDouble(attr.getAttribute("width"));
            level.getGameObjectFactory().createWall(width, height).setPosition(this.getPoint(i, "Position", "Wall", doc));
        }
    }
}

