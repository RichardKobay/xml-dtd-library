package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.ElementAlreadyExistsException;
import dev.soriane.dtdxmlparser.exceptions.ElementDoesNotExistsException;
import dev.soriane.dtdxmlparser.exceptions.MalformedXMLException;
import dev.soriane.dtdxmlparser.generator.XMLGenerator;
import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.Constraint;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnManagementTest {

    String resourcesPath = Paths.get(System.getProperty("user.dir")).resolve("src/main/resources").toString();
    String baseFolder = resourcesPath + "/testing/xml-manipulation/";
    String xmlPath = baseFolder + "/base-xml.xml";

    XMLGenerator xmlGenerator;
    XMLStructure xmlStructure;
    XMLParser xmlParser;

    @BeforeEach
    void setUp() throws IOException, ParserConfigurationException, SAXException, ElementAlreadyExistsException {
        xmlGenerator = new XMLGenerator();
        xmlParser = new XMLParser();

        xmlStructure = xmlParser.parse(FileManager.readFile(xmlPath));
        addNecessaryTables();
    }

    @AfterEach
    void tearDown() {
        xmlGenerator = null;
        xmlParser = null;

        xmlStructure = null;
    }

    @Test
    void columnExists_true_01() throws MalformedXMLException, ElementDoesNotExistsException {
        assertTrue(ColumnManagement.columnExists(xmlStructure, "table_name_01", "column_name_02_01"));
    }

    @Test
    void columnExists_true_02() throws MalformedXMLException, ElementDoesNotExistsException {
        assertTrue(ColumnManagement.columnExists(xmlStructure, "table_name_02", "column_name_04_02"));
    }

    @Test
    void columnExists_true_03() throws MalformedXMLException, ElementDoesNotExistsException {
        assertTrue(ColumnManagement.columnExists(xmlStructure, "table_name_04", "column_name_05_03"));
    }

    @Test
    void columnExists_true_04() throws MalformedXMLException, ElementDoesNotExistsException {
        assertTrue(ColumnManagement.columnExists(xmlStructure, "table_name_05", "column_name_05_03"));
    }

    @Test
    void columnExists_true_05() throws MalformedXMLException, ElementDoesNotExistsException {
        assertTrue(ColumnManagement.columnExists(xmlStructure, "table_name_07", "column_name_01_04"));
    }

    @Test
    void columnExists_false_01() throws MalformedXMLException, ElementDoesNotExistsException {
        assertFalse(ColumnManagement.columnExists(xmlStructure, "table_name_07", "id"));
    }

    @Test
    void columnExists_false_02() throws MalformedXMLException, ElementDoesNotExistsException {
        assertFalse(ColumnManagement.columnExists(xmlStructure, "table_name_07", "user"));
    }

    @Test
    void columnExists_false_03() throws MalformedXMLException, ElementDoesNotExistsException {
        assertFalse(ColumnManagement.columnExists(xmlStructure, "table_name_07", "email"));
    }

    @Test
    void columnExists_false_04() throws MalformedXMLException, ElementDoesNotExistsException {
        assertFalse(ColumnManagement.columnExists(xmlStructure, "table_name_07", "first_name"));
    }

    @Test
    void columnExists_false_05() throws MalformedXMLException, ElementDoesNotExistsException {
        assertFalse(ColumnManagement.columnExists(xmlStructure, "table_name_07", "last_name"));
    }

    @Test
    void columnExists_exception_01(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.columnExists(xmlStructure, "table_name_078", "last_name"));
    }

    @Test
    void columnExists_exception_02() {
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.columnExists(xmlStructure, "tablita", "last_name"));
    }

    @Test
    void columnExists_exception_03() {
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.columnExists(xmlStructure, "no existo", "last_name"));
    }

    @Test
    void columnExists_exception_04() {
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.columnExists(xmlStructure, "ni lo intentes", "last_name"));
    }

    @Test
    void columnExists_exception_05() {
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.columnExists(xmlStructure, "otro error", "last_name"));
    }

    @Test
    void getTableColumns_true_01() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_02", "number");
        columns.put("column_name_02_02", "varchar");
        columns.put("column_name_03_02", "boolean");
        columns.put("column_name_04_02", "varchar");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_02"));
    }

    @Test
    void getTableColumns_true_02() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_01", "number");
        columns.put("column_name_02_01", "varchar");
        columns.put("column_name_03_01", "boolean");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_01"));
    }

    @Test
    void getTableColumns_true_03() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_03", "number");
        columns.put("column_name_02_03", "varchar");
        columns.put("column_name_03_03", "boolean");
        columns.put("column_name_04_03", "varchar");
        columns.put("column_name_05_03", "varchar");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_04"));
    }

    @Test
    void getTableColumns_true_04() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_03", "number");
        columns.put("column_name_02_03", "varchar");
        columns.put("column_name_03_03", "boolean");
        columns.put("column_name_04_03", "varchar");
        columns.put("column_name_05_03", "varchar");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_05"));
    }

    @Test
    void getTableColumns_true_05() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_04", "number");
        columns.put("column_name_02_04", "varchar");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_07"));
    }

    @Test
    void getTableColumns_true_06() throws MalformedXMLException, ElementDoesNotExistsException {
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_04", "number");
        columns.put("column_name_02_04", "varchar");

        assertEquals(columns, ColumnManagement.getTableColumns(xmlStructure, "table_name_07"));
    }

    @Test
    void getTableColumns_exception_01(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.getTableColumns(xmlStructure, "table_name_08"));
    }

    @Test
    void getTableColumns_exception_02(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.getTableColumns(xmlStructure, "tablita"));
    }

    @Test
    void getTableColumns_exception_03(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.getTableColumns(xmlStructure, "no existo"));
    }

    @Test
    void getTableColumns_exception_04(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.getTableColumns(xmlStructure, "table_that_does_not_exists"));
    }

    @Test
    void getTableColumns_exception_05(){
        assertThrows(ElementDoesNotExistsException.class, () -> ColumnManagement.getTableColumns(xmlStructure, "_table"));
    }

    void addNecessaryTables() throws ElementAlreadyExistsException, IOException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-04.xml";

        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_name_01_02", "number");
        columns.put("column_name_02_02", "varchar");
        columns.put("column_name_03_02", "boolean");
        columns.put("column_name_04_02", "varchar");

        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint_01 = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        Constraint constraint_02 = new Constraint(
                "column_name_02_02",
                "constraint_type_02_02",
                "constraint_name_02_02",
                "table_reference_02_02",
                "column_reference_02_02"
        );

        constraints.add(constraint_01);
        constraints.add(constraint_02);

        XMLUtil.addTable(xmlStructure, "table_name_02", columns, constraints);
        XMLUtil.addTable(xmlStructure, "table_name_03", columns, constraints);

        columns = new LinkedHashMap<>();
        columns.put("column_name_01_03", "number");
        columns.put("column_name_02_03", "varchar");
        columns.put("column_name_03_03", "boolean");
        columns.put("column_name_04_03", "varchar");
        columns.put("column_name_05_03", "varchar");

        constraints = new ArrayList<>();
        Constraint constraint_01_02 = new Constraint(
                "column_name_01_03",
                "constraint_type_01_03",
                "constraint_name_01_03",
                "table_reference_01_03",
                "column_reference_01_03"
        );
        Constraint constraint_02_02 = new Constraint(
                "column_name_02_03",
                "constraint_type_02_03",
                "constraint_name_02_03",
                "table_reference_02_03",
                "column_reference_02_03"
        );
        Constraint constraint_03_02 = new Constraint(
                "column_name_03_03",
                "constraint_type_03_03",
                "constraint_name_03_03",
                "table_reference_03_03",
                "column_reference_03_03"
        );
        constraints.add(constraint_01_02);
        constraints.add(constraint_02_02);
        constraints.add(constraint_03_02);

        XMLUtil.addTable(xmlStructure, "table_name_04", columns, constraints);
        XMLUtil.addTable(xmlStructure, "table_name_05", columns, constraints);

        columns = new LinkedHashMap<>();
        columns.put("column_name_01_04", "number");
        columns.put("column_name_02_04", "varchar");

        constraints = new ArrayList<>();
        Constraint constraint_01_03 = new Constraint(
                "column_name_01_04",
                "constraint_type_01_04",
                "constraint_name_01_04"
        );
        Constraint constraint_02_03 = new Constraint(
                "column_name_02_04",
                "constraint_type_02_04",
                "constraint_name_02_04"
        );
        constraints.add(constraint_01_03);
        constraints.add(constraint_02_03);

        XMLUtil.addTable(xmlStructure, "table_name_06", columns, constraints);
        XMLUtil.addTable(xmlStructure, "table_name_07", columns, constraints);
    }
}