package dev.soriane.dtdxmlparser.validator;

import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import dev.soriane.dtdxmlparser.parser.DTDParser;
import dev.soriane.dtdxmlparser.parser.XMLParser;
import dev.soriane.plibs.file.FileManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DTDValidatorTest {

    String  resourcesPath = Paths.get(System.getProperty("user.dir")).resolve("src/main/resources").toString();
    String dtdPath_01 = resourcesPath + "/testing/xml-dtd-validator/testDTD.dtd";
    String dtdPath_02 = resourcesPath + "/testing/xml-dtd-validator/testDTD_02.dtd";
    String dtdPath_03 = resourcesPath + "/testing/xml-dtd-validator/testDTD_03.dtd";

    DTDValidator validator = new DTDValidator();

    XMLParser xmlParser;
    DTDParser dtdParser;
    DTDStructure dtdStructure_01;
    DTDStructure dtdStructure_02;
    XMLStructure xmlStructure;

    @BeforeEach
    void setUp() throws IOException {
        xmlParser = new XMLParser();
        dtdParser = new DTDParser();
        dtdStructure_01 = dtdParser.parse(FileManager.readFile(dtdPath_01));
        dtdStructure_02 = dtdParser.parse(FileManager.readFile(dtdPath_02));
    }

    @AfterEach
    void tearDown() {
        xmlParser = null;
        dtdParser = null;
        dtdStructure_01 = null;
        dtdStructure_02 = null;
        xmlStructure = null;
    }

    @Test
    void validate_01() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_01.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_02() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_02.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_03() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_03.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_04() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_04.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_05() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_05.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_06() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXML_06.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_01() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_01.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_02() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_02.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_03() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_03.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_04() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_04.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_05() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_05.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_06() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_06.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_01_false_07() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/testXMLFalse_07.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_01() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_01.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_02() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_02.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_03() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_03.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_04() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_04.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_05() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_05.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_true_06() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_06.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_01() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_01.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_02() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_02.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_03() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_03.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_04() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_04.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_05() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_05.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_06() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_06.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_02_false_07() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_02_XML_false_07.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertFalse(validator.validate(xmlStructure, dtdStructure_01));
    }

    @Test
    void validate_dtd_03_true_01() throws Exception {
        String xmlPath = resourcesPath + "/testing/xml-dtd-validator/test_DTD_03_XML_01.xml";
        String xmlString = FileManager.readFile(xmlPath);
        xmlStructure = xmlParser.parse(xmlString);

        assertTrue(validator.validate(xmlStructure, dtdStructure_01));
    }
}
