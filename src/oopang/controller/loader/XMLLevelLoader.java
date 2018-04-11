package oopang.controller.loader;

import java.io.File;
import java.util.Optional;

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
import oopang.model.levels.LevelBuilder;
import oopang.model.powers.PowerFactory;
import oopang.model.powers.PowerTag;
import oopang.view.rendering.ImageID;

/**
 * Class that load the XMLFile into the level.
 */
public class XMLLevelLoader implements LevelLoader {

    private static final String PATH = "/levels/";
    private static final String ROOT = "Level";

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
    public LevelData loadInfiniteLevel(final LevelBuilder builder) {
        return loadLevel(Optional.empty(), builder);
    }

    @Override
    public LevelData loadStoryLevel(final int index, final LevelBuilder builder) {
        return loadLevel(Optional.of(index), builder);
    }

    /**
     * Method that loads informations from XML file an decorate the appropriate {@link Level}. 
     * @param index
     *      the index of the {@link StoryModeGameSession}
     * @return
     *      A {@link LevelData} decorated
     */
    private LevelData loadLevel(final Optional<Integer> index, final LevelBuilder builder) {
        final String path;
        if (index.isPresent()) {
            path = PATH + ROOT + index.get() + ".xml";
        } else {
            path = PATH + "InfiniteLevel.xml";
        }
        try {
            final DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            final File file = new File(this.getClass().getResource(path).toURI());
            final DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            this.loadLevelData(builder, doc);
            if (index.isPresent()) {
                final ImageID wallTexture = this.loadWallTexture(doc);
                final ImageID background = this.loadBackground(doc);
                this.getPowers(builder, doc);
                this.loadBall(builder, doc);
                this.loadWalls(builder, doc);
                return new LevelData(background, wallTexture, builder.build());
            } else {
                this.getPowers(builder, doc);
                return new LevelData(ImageID.getRandomBackground(), ImageID.getRandomWallTexture(), builder.build());
            }
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
    private void getPowers(final LevelBuilder level, final Document doc) {
        final NodeList powers = doc.getElementsByTagName("AvailablePower");
        final Node availablePowers = powers.item(0);
        final Element powerField = (Element) availablePowers;
        final int length = powerField.getElementsByTagName("Power").getLength();
        for (int i = 0; i < length; i++) {
            final Element power = (Element) powerField.getElementsByTagName("Power").item(i);
            final PowerTag pow = PowerTag.valueOf(power.getAttribute("id"));
            if (pow == PowerTag.ADHESIVESHOT) {
                level.addAvailablePower(() -> this.powerFactory.createAdhesiveShot());
            }
            if (pow == PowerTag.DOUBLESHOT) {
                level.addAvailablePower(() -> this.powerFactory.createDoubleShot());
            }
            if (pow == PowerTag.DOUBLESPEED) {
                level.addAvailablePower(() -> this.powerFactory.createDoubleSpeed());
            }
            if (pow == PowerTag.FREEZE) {
                level.addAvailablePower(() -> this.powerFactory.createFreeze());
            }
            if (pow == PowerTag.TIMEDSHIELD) {
                level.addAvailablePower(() -> this.powerFactory.createTimedShield());
            }
            if (pow == PowerTag.DYNAMITE) {
                level.addAvailablePower(() -> this.powerFactory.createDynamite());
            }
        }
    }

    /**
     * Get the {@link Ball} attributes and use the {@link GameObjectFactory} to generate new balls.
     * @param level
     *      The reference of the {@link Level}
     * @param doc
     *      The document to load
     */
    private void loadBall(final LevelBuilder level, final Document doc) {
        final NodeList balls = doc.getElementsByTagName("Ball");
        for (int k = 0; k < balls.getLength(); k++) {
            final Node ball = balls.item(k);
            final Element attr = (Element) ball;
            final BallColor color = BallColor.valueOf(attr.getAttribute("color"));
            final int size = Integer.parseInt(attr.getAttribute("size"));
            level.addBall(size, getVector(k, "Velocity", "Ball", doc), color, getPoint(k, "Position", "Ball", doc));
        }
    }

    /**
     * Getter for {@link Vector2D} field in a XML document.
     * @param doc
     *      The document containing the {@link Level}.
     * @param item
     *      The number of {@link Vector2D} tag in the TreeNode
     * @param node
     *      The name of the TreeNode.
     * @param doc
     *      The document to load
     * @return
     *      The {@link Vector2D} searched.
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
     */
    private void loadLevelData(final LevelBuilder level, final Document doc) {
        final NodeList nList = doc.getElementsByTagName(ROOT);
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        if (eElement.getElementsByTagName("Time").getLength() > 0) {
            level.setTime(Double.parseDouble(eElement.getElementsByTagName("Time").item(0).getTextContent()));
        } else {
            level.setSurvival();
        }
        if (eElement.getElementsByTagName("Gravity").getLength() > 0) {
            level.setBallGravity(getVector(0, "Gravity", "Level", doc));
        }
    }

    /**
     * Method to load the {@link ImageID} of the Background in this {@link Level}.
     * The XML File
     * @return
     *      The texture of the walls.
     */
    private ImageID loadBackground(final Document doc) {
        final NodeList nList = doc.getElementsByTagName(ROOT);
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        return ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
    }

    /**
     * Method to load the {@link ImageID} of the {@link Wall} in this {@link Level}.
     * @param doc
     *      The XML File
     * @return
     *      The texture of the walls.
     */
    private ImageID loadWallTexture(final Document doc) {
        final NodeList nList = doc.getElementsByTagName(ROOT);
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        return ImageID.valueOf(eElement.getElementsByTagName("WallTexture").item(0).getTextContent());
    }

    /**
     * Load {@link Wall} from the XML file.
     * @param level
     *      The instance of {@link Level} where create {@link Wall}
     */
    private void loadWalls(final LevelBuilder level, final Document doc) {
        final NodeList wallList = doc.getElementsByTagName("Wall");
        for (int i = 0; i < wallList.getLength(); i++) {
            final Node wall = wallList.item(i);
            final Element attr = (Element) wall;
            final double height = Double.parseDouble(attr.getAttribute("height"));
            final double width = Double.parseDouble(attr.getAttribute("width"));
            level.addWall(width, height, getPoint(i, "Position", "Wall", doc));
        }
    }
}

