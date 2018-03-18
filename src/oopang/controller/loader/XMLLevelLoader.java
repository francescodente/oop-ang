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

import oopang.view.rendering.ImageID;

public class XMLLevelLoader implements LevelLoader {

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;

    public XMLLevelLoader() throws ParserConfigurationException {
        super();
        this.dbFactory = DocumentBuilderFactory.newInstance();
        this.dBuilder = dbFactory.newDocumentBuilder();
    }

    @Override
    public LevelData loadInfiniteLevel() throws SAXException, IOException {
        // TODO Auto-generated method stub
        final String s = "";
        final File xmlFile = new File(s);
        final Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        final NodeList nList = doc.getElementsByTagName("Level");
        Node nNode = nList.item(0);
        Element eElement = (Element) nNode;
        final ImageID background = ImageID.valueOf(eElement.getElementsByTagName("Background").item(0).getTextContent());
        //TODO try to reduce lines
        return null;
    }

    @Override
    public LevelData loadStoryLevel(Integer index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LevelData loadTutorial() {
        // TODO Auto-generated method stub
        return null;
    }
}
