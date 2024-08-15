package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.*;
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

class XMLUtilTest {
    String resourcesPath = Paths.get(System.getProperty("user.dir")).resolve("src/main/resources").toString();
    String baseFolder = resourcesPath + "/testing/xml-manipulation/";
    String dtdPath = resourcesPath + "/file.dtd";
    String xmlPath = baseFolder + "/base-xml.xml";

    XMLGenerator xmlGenerator;
    DTDStructure dtdStructure;
    XMLStructure xmlStructure;
    DTDParser dtdParser;
    XMLParser xmlParser;

    @BeforeEach
    void setUp() throws IOException, ParserConfigurationException, SAXException {
        xmlGenerator = new XMLGenerator();
        xmlParser = new XMLParser();
        dtdParser = new DTDParser();

        dtdStructure = dtdParser.parse(FileManager.readFile(dtdPath));
        xmlStructure = xmlParser.parse(FileManager.readFile(xmlPath));
    }

    @AfterEach
    void tearDown() {
        xmlGenerator = null;
        xmlParser = null;
        dtdParser = null;

        dtdStructure = null;
        xmlStructure = null;
    }

    @Test
    void addTable_01() throws IOException, ElementAlreadyExistsException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

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

        String generatedXMLString = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXMLString);
    }

    @Test
    void addTable_02() throws IOException, ElementAlreadyExistsException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

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

        XMLUtil.addTable(xmlStructure, "table_name_03", columns, constraints);

        String generatedXMLString = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXMLString);
    }

    @Test
    void addTable_03() throws IOException, ElementAlreadyExistsException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-03.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

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

        XMLUtil.addTable(xmlStructure, "table_name_03", columns, constraints);

        columns = new LinkedHashMap<>();
        columns.put("column_name_01_04", "number");
        columns.put("column_name_02_04", "varchar");

        constraints = new ArrayList<>();
        Constraint constraint_01_03 = new Constraint(
                "column_name_01_04",
                "constraint_type_01_04",
                "constraint_name_01_04",
                "table_reference_01_04",
                "column_reference_01_04"
        );
        Constraint constraint_02_03 = new Constraint(
                "column_name_02_04",
                "constraint_type_02_04",
                "constraint_name_02_04",
                "table_reference_02_04",
                "column_reference_02_04"
        );
        constraints.add(constraint_01_03);
        constraints.add(constraint_02_03);

        XMLUtil.addTable(xmlStructure, "table_name_04", columns, constraints);

        String generatedXMLString = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXMLString);
    }

    @Test
    void addTable_04() throws IOException, ElementAlreadyExistsException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

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

        XMLUtil.addTable(xmlStructure, "table_name_03", columns, constraints);

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

        XMLUtil.addTable(xmlStructure, "table_name_04", columns, constraints);

        String generatedXMLString = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXMLString);
    }

    @Test
    void addTable_05() throws IOException, ElementAlreadyExistsException {
        String finalXMLPath = baseFolder + "adding-tables/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

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

        XMLUtil.addTable(xmlStructure, "table_name_03", columns, constraints);

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

        XMLUtil.addTable(xmlStructure, "table_name_04", columns, constraints);

        LinkedHashMap<String, String> finalColumns = columns;
        List<Constraint> finalConstraints = constraints;
        assertThrows(ElementAlreadyExistsException.class, () ->XMLUtil.addTable(xmlStructure, "table_name_03", finalColumns, finalConstraints));
    }

    @Test
    void dropTable_01() throws ElementAlreadyExistsException, IOException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-tables/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);
        addNecessaryColumns();
        XMLUtil.dropTable(xmlStructure, "table_name_02");

        String generatedXml = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXml);
    }

    @Test
    void dropTable_02() throws ElementAlreadyExistsException, IOException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-tables/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);
        addNecessaryColumns();
        XMLUtil.dropTable(xmlStructure, "table_name_07");

        String generatedXml = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXml);
    }

    @Test
    void dropTable_03() throws ElementAlreadyExistsException, IOException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-tables/result-xml-03.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);
        addNecessaryColumns();
        XMLUtil.dropTable(xmlStructure, "table_name_01");
        XMLUtil.dropTable(xmlStructure, "table_name_04");

        String generatedXml = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXml);
    }

    @Test
    void dropTable_04() throws ElementAlreadyExistsException, IOException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-tables/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);
        addNecessaryColumns();
        XMLUtil.dropTable(xmlStructure, "table_name_06");
        XMLUtil.dropTable(xmlStructure, "table_name_07");

        String generatedXml = XMLGenerator.xmlStructureString(xmlStructure);

        assertEquals(finalXMLString, generatedXml);
    }

    @Test
    void dropTable_05() throws ElementAlreadyExistsException, IOException{
        String finalXMLPath = baseFolder + "removing-tables/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropTable(xmlStructure, "table_name_022"));
    }

    @Test
    void addColumn_01() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-columns/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();
        XMLUtil.addColumn(xmlStructure, "table_name_01", "is_verified", "boolean");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addColumn_02() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-columns/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();
        XMLUtil.addColumn(xmlStructure, "table_name_06", "bool_type", "boolean");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addColumn_03() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-columns/result-xml-03.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();
        XMLUtil.addColumn(xmlStructure, "table_name_03", "una", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_03", "columna", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_03", "mas", "varchar");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addColumn_04() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-columns/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();
        XMLUtil.addColumn(xmlStructure, "table_name_01", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_02", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_03", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_04", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_05", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_06", "varchar_column", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_07", "varchar_column", "varchar");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addColumn_05() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.addColumn(xmlStructure, "table_name_07", "id", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_07", "name", "varchar");
        XMLUtil.addColumn(xmlStructure, "table_name_07", "email", "varchar");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addColumn_06() throws ElementAlreadyExistsException, IOException {
        addNecessaryColumns();
        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_01", "column_name_01_01", "varchar"));
    }

    @Test
    void addColumn_07() throws ElementAlreadyExistsException, IOException {
        addNecessaryColumns();
        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_07", "column_name_02_04", "varchar"));
    }

    @Test
    void addColumn_08() throws ElementAlreadyExistsException, IOException {
        addNecessaryColumns();
        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_06", "column_name_02_04", "varchar"));
    }

    @Test
    void addColumn_09() throws ElementAlreadyExistsException, IOException {
        addNecessaryColumns();
        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_02", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_10() throws ElementAlreadyExistsException, IOException {
        addNecessaryColumns();
        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_03", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_11() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_08", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_12() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table_name_8", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_13() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "users", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_14() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "table", "column_name_01_02", "varchar"));
    }

    @Test
    void addColumn_15() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addColumn(xmlStructure, "i do not exists", "column_name_01_02", "varchar"));
    }

    @Test
    void editColumn_01() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "editing-columns/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.editColumn(xmlStructure, "table_name_01", "column_name_03_01", "new_name", "boolean");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void editColumn_02() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "editing-columns/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.editColumn(xmlStructure, "table_name_02", "column_name_04_02", "column_name_04_02", "new_data_type");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void editColumn_03() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "editing-columns/result-xml-03.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.editColumn(xmlStructure, "table_name_03", "column_name_04_02", "new_name", "new_data_type");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void editColumn_04() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "editing-columns/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.addColumn(xmlStructure, "table_name_05", "new_column", "varchar");
        XMLUtil.editColumn(xmlStructure, "table_name_05", "new_column", "new_column_changed", "new_column_new_data_type");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void editColumn_05() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "editing-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.editColumn(xmlStructure, "table_name_07", "column_name_02_04", "is_verified", "boolean");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void editColumn_06() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.editColumn(xmlStructure, "table_name_01", "non_existent", "new_column_name", "new_data_type"));
    }

    @Test
    void editColumn_07() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.editColumn(xmlStructure, "non_existent", "column_name_01_01", "new_column_name", "new_data_type"));
    }

    @Test
    void editColumn_08() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.editColumn(xmlStructure, "non_existent", "non_existent", "new_column_name", "new_data_type"));
    }

    @Test
    void editColumn_09() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.editColumn(xmlStructure, "si", "no", "new_column_name", "new_data_type"));
    }

    @Test
    void editColumn_10() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.editColumn(xmlStructure, "no", "si", "new_column_name", "new_data_type"));
    }

    @Test
    void dropColumn_01() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.dropColumn(xmlStructure, "table_name_01", "column_name_03_01");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void dropColumn_02() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.dropColumn(xmlStructure, "table_name_02", "column_name_01_02");
        XMLUtil.dropColumn(xmlStructure, "table_name_02", "column_name_02_02");
        XMLUtil.dropColumn(xmlStructure, "table_name_02", "column_name_03_02");
        XMLUtil.dropColumn(xmlStructure, "table_name_02", "column_name_04_02");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void dropColumn_03() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-03.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.dropColumn(xmlStructure, "table_name_04", "column_name_02_03");
        XMLUtil.dropColumn(xmlStructure, "table_name_04", "column_name_04_03");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void dropColumn_04() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-04.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.dropColumn(xmlStructure, "table_name_06", "column_name_01_04");
        XMLUtil.dropColumn(xmlStructure, "table_name_06", "column_name_02_04");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void dropColumn_05() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        XMLUtil.dropColumn(xmlStructure, "table_name_07", "column_name_01_04");
        XMLUtil.dropColumn(xmlStructure, "table_name_07", "column_name_02_04");

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void dropColumn_06() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropColumn(xmlStructure, "table_name_01", "column_name_01_04"));
    }

    @Test
    void dropColumn_07() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropColumn(xmlStructure, "table_name_01", "id"));
    }

    @Test
    void dropColumn_08() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropColumn(xmlStructure, "no", "existo"));
    }

    @Test
    void dropColumn_09() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropColumn(xmlStructure, "yo", "column_name_01_04"));
    }

    @Test
    void dropColumn_10() {
        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.dropColumn(xmlStructure, "tampoco", "column_name_01_04"));
    }

    @Test
    void addConstraint_01() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-constraints/result-xml-01.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "pk-constraint",
                "column_name_01_01-pk-constraint"
        );

        XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint);

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addConstraint_02() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "adding-constraints/result-xml-02.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "pk-constraint",
                "column_name_01_01-pk-constraint",
                "table_name_02",
                "column_name_02_02"
        );

        XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint);

        assertEquals(finalXMLString, XMLGenerator.xmlStructureString(xmlStructure));
    }

    @Test
    void addConstraint_03() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "pk-constraint",
                "column_name_01_01-pk-constraint",
                "table_name_02",
                "no existo"
        );

        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint));
    }

    @Test
    void addConstraint_05() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-05.xml";

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "pk-constraint",
                "column_name_01_01-pk-constraint",
                "no existo",
                "column_name_02_02"
        );

        assertThrows(ElementDoesNotExistsException.class, () -> XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint));
    }

    @Test
    void addConstraint_06() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "pk-constraint",
                "constraint_name_01_01",
                "table_name_02",
                "column_name_02_02"
        );

        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint));
    }

    @Test
    void addConstraint_07() throws IOException, ElementAlreadyExistsException, MalformedXMLException, ElementDoesNotExistsException {
        String finalXMLPath = baseFolder + "removing-columns/result-xml-05.xml";
        String finalXMLString = FileManager.readFile(finalXMLPath);

        addNecessaryColumns();

        Constraint constraint = new Constraint(
                "column_name_01_01",
                "constraint_type_01_01",
                "column_name_01_01-pk-constraint",
                "table_name_02",
                "column_name_02_02"
        );

        assertThrows(ElementAlreadyExistsException.class, () -> XMLUtil.addConstraint(xmlStructure, "table_name_01", constraint));
    }

    @Test
    void dropConstraint() {

    }

    void addNecessaryColumns() throws ElementAlreadyExistsException, IOException {
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
