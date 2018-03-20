package oopang.controller.loader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import oopang.commons.space.Point2D;
import oopang.commons.space.Points2D;
import oopang.commons.space.Vector2D;
import oopang.commons.space.Vectors2D;
import oopang.model.BallColor;
import oopang.model.gameobjects.Wall;
import oopang.model.levels.BaseLevel;
import oopang.model.levels.InfiniteLevel;
import oopang.model.levels.Level;
import oopang.model.levels.TimedLevel;
import oopang.view.rendering.ImageID;

/**
 * Class that load the XMLFile into the level.
 */
public class XMLLevelLoader implements LevelLoader {

    private final DocumentBuilderFactory dbFactory;
    private final DocumentBuilder dBuilder;
    private Level level;
    private String path;
    private File file;
    private Document doc;

    /**
     * Constructor of the Class setting the base path.
     * @throws ParserConfigurationException
     *      Exception of the XML parsing.
     */
    public XMLLevelLoader() throws ParserConfigurationException {
        super();
        this.dbFactory = DocumentBuilderFactory.newInstance();
        this.dBuilder = dbFactory.newDocumentBuilder();
        this.path = "/res/levels/";
    }

    @Override
    public LevelData loadInfiniteLevel() throws SAXException, IOException {
        this.level = new InfiniteLevel(new BaseLevel());
        this.path += "InfiniteLevel.xml";
        this.file = new File(this.path);
        this.doc = dBuilder.parse(file);
        this.doc.getDocumentElement().normalize();
        final ImageID background = this.loadLevelData(this.level);
        return new LevelData(background, this.level);
    }

    @Override
    public LevelData loadStoryLevel(final Integer index) throws SAXException, IOException {
        this.path += "Level" + index + ".xml";
        final File file = new File(this.path);
        this.doc = dBuilder.parse(file);
        this.doc.getDocumentElement().normalize();
        final ImageID background = this.loadLevelData(this.level);
        //this.level = getPowers(doc, this.level);
        this.loadBall(this.level);
        this.loadWalls(this.level);
        return new LevelData(background, this.level);
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }
/*
    private void getPowers(final Level level) {
        final NodeList powers = this.doc.getElementsByTagName("AvailablePower");
        final Node availablePowers = powers.item(0);
        final Element powerField = (Element) availablePowers;
        final int length = powerField.getElementsByTagName("Power").getLength();
        for (int i = 0; i < length; i++) {
            final Element power = (Element) powerField.getElementsByTagName("Power").item(i);
        }
        return null;
    }
*/
    /**
     * Get the {@link Ball} attributes and use the {@link GameObjectFactory} to generate new balls.
     * @param level
     *      The reference of the {@link Level}
     * @param doc
     */
    private void loadBall(final Level level) {
        final NodeList balls = this.doc.getElementsByTagName("Ball");
        for (int k = 0; k < balls.getLength(); k++) {
            final Node ball = balls.item(k);
            final Element attr = (Element) ball;
            final BallColor color = BallColor.valueOf(attr.getAttribute("color"));
            final int size = Integer.parseInt(attr.getAttribute("size"));
            level.getGameObjectFactory().createBall(size, getVector(k, "Velocity"), color).setPosition(this.getPosition(k));
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
     * @return
     */
    private Vector2D getVector(final int item, final String node) {
        final NodeList vector = this.doc.getElementsByTagName(node);
        final Node ballField = vector.item(item);
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
    private Point2D getPosition(final int item) {
        final NodeList position = this.doc.getElementsByTagName("Position");
        final Node positionField = position.item(item);
        final Element positionElement = (Element) positionField;
        final Element positionBall = (Element) positionElement.getElementsByTagName("Point").item(0);
        return Points2D.of(Double.valueOf(positionBall.getAttribute("x")), Double.valueOf(positionBall.getAttribute("y")));
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
    private ImageID loadLevelData(final Level level) {
        final NodeList nList = this.doc.getElementsByTagName("Level");
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        final ImageID background = ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
        this.level = new TimedLevel(new BaseLevel(), Double.parseDouble(eElement.getElementsByTagName("Time").item(0).getTextContent()));
        return background;
    }

    /**
     * Load {@link Wall} from the XML file.
     * @param level
     *      The instance of {@link Level} where create {@link Wall}
     */
    private void loadWalls(final Level level) {
        final NodeList wallList = this.doc.getElementsByTagName("Wall");
        for (int i = 0; i < wallList.getLength(); i++) {
            final Node wall = wallList.item(i);
            final Element attr = (Element) wall;
            final double height = Double.parseDouble(attr.getAttribute("height"));
            final double width = Double.parseDouble(attr.getAttribute("width"));
            level.getGameObjectFactory().createWall(width, height).setPosition(this.getPosition(i));
        }
    }
    
    //TODO TO FIX BUG "POSITION" 
}
