package dev.soriane.dtdxmlparser.generator;

import dev.soriane.dtdxmlparser.exceptions.NeedChildElementException;
import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import dev.soriane.dtdxmlparser.parser.DTDParser;
import dev.soriane.dtdxmlparser.parser.XMLParser;
import dev.soriane.plibs.file.FileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class XMLGeneratorTest {
    String resourcesPath = Paths.get(System.getProperty("user.dir")).resolve("src/main/resources").toString();
    String baseFolder = resourcesPath + "/testing/xml-generator/";
    String dtdPath = resourcesPath + "/file.dtd";
    String xmlPath = baseFolder + "/base-xml_01.xml";

    XMLGenerator xmlGenerator;
    DTDStructure dtdStructure;
    XMLStructure xmlStructure;
    DTDParser dtdParser;
    XMLParser xmlParser;

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        xmlGenerator = new XMLGenerator();
        dtdParser = new DTDParser();
        xmlParser = new XMLParser();

        dtdStructure = dtdParser.parse(FileManager.readFile(dtdPath));
        xmlStructure = xmlParser.parse(FileManager.readFile(xmlPath));
    }

    @AfterEach
    void tearDown() {
        xmlGenerator = null;
        dtdParser = null;
        xmlParser = null;

        dtdStructure = null;
        xmlStructure = null;
    }

    @Test
    void generateEmptyXML_01() throws NeedChildElementException, IOException {
        String emptyXml = xmlGenerator.generateEmptyXML(dtdStructure, "database-name");
        String filePath = baseFolder + "empty-xml-01.xml";
        assertEquals(FileManager.readFile(filePath), emptyXml + "\n");
    }

    @Test
    void generateEmptyXML_02() throws NeedChildElementException, IOException {
        String emptyXml = xmlGenerator.generateEmptyXML(dtdStructure, "ivan-db");
        String filePath = baseFolder + "empty-xml-02.xml";
        assertEquals(FileManager.readFile(filePath), emptyXml + "\n");
    }

    @Test
    void generateEmptyXML_03() throws NeedChildElementException, IOException {
        String emptyXml = xmlGenerator.generateEmptyXML(dtdStructure, "");
        String filePath = baseFolder + "empty-xml-03.xml";
        assertEquals(FileManager.readFile(filePath), emptyXml + "\n");
    }

    @Test
    void generateEmptyXML_04() throws NeedChildElementException, IOException {
        String emptyXml = xmlGenerator.generateEmptyXML(dtdStructure, "nombre-para-db");
        String filePath = baseFolder + "empty-xml-04.xml";
        assertEquals(FileManager.readFile(filePath), emptyXml + "\n");
    }

    @Test
    void generateEmptyXML_05() throws NeedChildElementException, IOException {
        String emptyXml = xmlGenerator.generateEmptyXML(dtdStructure, "dname");
        String filePath = baseFolder + "empty-xml-05.xml";
        assertEquals(FileManager.readFile(filePath), emptyXml + "\n");
    }

    @Test
    void xmlStructureString_01() throws IOException, ParserConfigurationException, SAXException {
        xmlStructure = xmlParser.parse(FileManager.readFile(baseFolder + "base-xml_01.xml"));
        String xmlStr = XMLGenerator.xmlStructureString(xmlStructure);
        assertEquals(FileManager.readFile(baseFolder + "final-xml-01.xml"), xmlStr);
    }

    @Test
    void xmlStructureString_02() throws IOException, ParserConfigurationException, SAXException {
        xmlStructure = xmlParser.parse(FileManager.readFile(baseFolder + "base-xml_02.xml"));
        String xmlStr = XMLGenerator.xmlStructureString(xmlStructure);
        assertEquals(FileManager.readFile(baseFolder + "final-xml-02.xml"), xmlStr);
    }

    @Test
    void xmlStructureString_03() throws IOException, ParserConfigurationException, SAXException {
        xmlStructure = xmlParser.parse(FileManager.readFile(baseFolder + "base-xml_03.xml"));
        String xmlStr = XMLGenerator.xmlStructureString(xmlStructure);
        assertEquals(FileManager.readFile(baseFolder + "final-xml-03.xml"), xmlStr);
    }

    @Test
    void xmlStructureString_04() throws IOException, ParserConfigurationException, SAXException {
        xmlStructure = xmlParser.parse(FileManager.readFile(baseFolder + "base-xml_04.xml"));
        String xmlStr = XMLGenerator.xmlStructureString(xmlStructure);
        assertEquals(FileManager.readFile(baseFolder + "final-xml-04.xml"), xmlStr);
    }

    @Test
    void xmlStructureString_05() throws IOException, ParserConfigurationException, SAXException {
        xmlStructure = xmlParser.parse(FileManager.readFile(baseFolder + "base-xml_05.xml"));
        String xmlStr = XMLGenerator.xmlStructureString(xmlStructure);
        assertEquals(FileManager.readFile(baseFolder + "final-xml-05.xml"), xmlStr);
    }

}