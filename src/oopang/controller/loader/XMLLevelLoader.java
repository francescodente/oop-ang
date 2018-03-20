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

import oopang.commons.space.Vector2D;
import oopang.model.BallColor;
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

    /**
     * Constructor of the Class setting the base path.
     * @throws ParserConfigurationException
     *      Exception of the xml parsing.
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
        final String s = "";
        final File file = new File(s);
        final Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        final NodeList nList = doc.getElementsByTagName("Level");
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        final ImageID background = ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
        return new LevelData(background, this.level);
    }

    @Override
    public LevelData loadStoryLevel(final Integer index) throws SAXException, IOException {
        this.path += "Level" + index + ".xml";
        final File file = new File(this.path);
        final Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        final NodeList nList = doc.getElementsByTagName("Level");
        final Node nNode = nList.item(0);
        final Element eElement = (Element) nNode;
        final ImageID background = ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
        this.level = new TimedLevel(new BaseLevel(), Double.parseDouble(eElement.getElementsByTagName("Time").item(0).getTextContent()));
        //TODO finish to decorate level
        return new LevelData(background, this.level);
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }

    private Vector2D getGravity(Document doc) {
        //TODO decide where and if set the gravity in the level.
        return null;
    }

    // private Power getPowers(Document doc);

    private void decorateBall(Level level, Document doc) {
        final NodeList balls = doc.getElementsByTagName("Ball");
        for (int k = 0; k < balls.getLength(); k++) {
            final Node ball = balls.item(k);
            final Element attr = (Element) ball;
            final BallColor color = BallColor.valueOf(attr.getAttribute("color"));
        }
    }
}
