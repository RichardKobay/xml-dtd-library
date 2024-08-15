package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.ElementAlreadyExistsException;
import dev.soriane.dtdxmlparser.exceptions.ElementDoesNotExistsException;
import dev.soriane.dtdxmlparser.generator.XMLGenerator;
import dev.soriane.dtdxmlparser.model.xml.Attribute;
import dev.soriane.dtdxmlparser.model.xml.Constraint;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
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

class ConstraintManagementTest {
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
    void getTableConstraints_01() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint = new Constraint(
                "column_name_01_01",
                "constraint_type_01_01",
                "constraint_name_01_01",
                "table_reference_01_01",
                "column_reference_01_01"
        );
        constraints.add(constraint);

        List<Constraint> tableConstraints = ConstraintManagement.getTableConstraints("table_name_01", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getTableConstraints_02() throws ElementDoesNotExistsException {
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

        List<Constraint> tableConstraints = ConstraintManagement.getTableConstraints("table_name_02", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getTableConstraints_03() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
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

        List<Constraint> tableConstraints = ConstraintManagement.getTableConstraints("table_name_04", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getTableConstraints_04() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
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

        List<Constraint> tableConstraints = ConstraintManagement.getTableConstraints("table_name_07", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getTableConstraints_05() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
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

        List<Constraint> tableConstraints = ConstraintManagement.getTableConstraints("table_name_06", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getTableConstraints_06() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getTableConstraints("table_name_08", xmlStructure));
    }

    @Test
    void getTableConstraints_07() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getTableConstraints("table_name_09", xmlStructure));
    }

    @Test
    void getTableConstraints_08() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getTableConstraints("users", xmlStructure));
    }

    @Test
    void getTableConstraints_09() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getTableConstraints("non-exists", xmlStructure));
    }

    @Test
    void getTableConstraints_10() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getTableConstraints("ivan", xmlStructure));
    }

    @Test
    void getColumnConstraints_01() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint = new Constraint(
                "column_name_01_01",
                "constraint_type_01_01",
                "constraint_name_01_01",
                "table_reference_01_01",
                "column_reference_01_01"
        );

        constraints.add(constraint);

        List<Constraint> tableConstraints = ConstraintManagement.getColumnConstraints("table_name_01", "column_name_01_01", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getColumnConstraints_02() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint_01 = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );

        constraints.add(constraint_01);

        List<Constraint> tableConstraints = ConstraintManagement.getColumnConstraints("table_name_02", "column_name_01_02", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getColumnConstraints_03() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint_02_02 = new Constraint(
                "column_name_02_03",
                "constraint_type_02_03",
                "constraint_name_02_03",
                "table_reference_02_03",
                "column_reference_02_03"
        );
        constraints.add(constraint_02_02);

        List<Constraint> tableConstraints = ConstraintManagement.getColumnConstraints("table_name_04", "column_name_02_03", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getColumnConstraints_04() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint_01_03 = new Constraint(
                "column_name_01_04",
                "constraint_type_01_04",
                "constraint_name_01_04"
        );
        constraints.add(constraint_01_03);

        List<Constraint> tableConstraints = ConstraintManagement.getColumnConstraints("table_name_06", "column_name_01_04", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getColumnConstraints_05() throws ElementDoesNotExistsException {
        List<Constraint> constraints = new ArrayList<>();
        Constraint constraint_02_03 = new Constraint(
                "column_name_02_04",
                "constraint_type_02_04",
                "constraint_name_02_04"
        );
        constraints.add(constraint_02_03);

        List<Constraint> tableConstraints = ConstraintManagement.getColumnConstraints("table_name_07", "column_name_02_04", xmlStructure);

        for (int i = 0; i < constraints.size(); i++) {
            Constraint finalConstraint = constraints.get(i);
            Constraint tableConstraint = tableConstraints.get(i);

            assertEquals(finalConstraint.getConstraintName(), tableConstraint.getConstraintName());
            assertEquals(finalConstraint.getConstraintType(), tableConstraint.getConstraintType());
            assertEquals(finalConstraint.getColumnName(), tableConstraint.getColumnName());
            assertEquals(finalConstraint.getTableReference(), tableConstraint.getTableReference());
            assertEquals(finalConstraint.getColumnReference(), tableConstraint.getColumnReference());
        }
    }

    @Test
    void getColumnConstraints_06() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getColumnConstraints("table_name_08", "column_name_01_04", xmlStructure));
    }

    @Test
    void getColumnConstraints_07() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getColumnConstraints("users", "column_name_01_04", xmlStructure));
    }

    @Test
    void getColumnConstraints_08() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getColumnConstraints("passwords", "column_name_01_04", xmlStructure));
    }

    @Test
    void getColumnConstraints_09() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getColumnConstraints("table_name_01", "id", xmlStructure));
    }

    @Test
    void getColumnConstraints_10() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.getColumnConstraints("table_name_02", "ivan", xmlStructure));
    }

    @Test
    void constraintExists_true_01() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_01",
                "constraint_type_01_01",
                "constraint_name_01_01",
                "table_reference_01_01",
                "column_reference_01_01"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_01", constraint));
    }

    @Test
    void constraintExists_true_02() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_02_02",
                "constraint_type_02_02",
                "constraint_name_02_02",
                "table_reference_02_02",
                "column_reference_02_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_02", constraint));
    }

    @Test
    void constraintExists_true_03() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_02", constraint));
    }

    @Test
    void constraintExists_true_04() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_02", constraint));
    }

    @Test
    void constraintExists_true_05() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_true_06() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_false_01() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "constraint_type_01_02",
                "constraint_name_01_02",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertTrue(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_false_02() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "changed",
                "changed",
                "table_reference_01_02",
                "column_reference_01_02"
        );
        assertFalse(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_false_03() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "changed",
                "changed",
                "changed",
                "changed"
        );
        assertFalse(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_false_04() throws ElementDoesNotExistsException {
        Constraint constraint = new Constraint(
                "column_name_01_02",
                "no",
                "si",
                "",
                ""
        );
        assertFalse(ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_throws_01() {
        Constraint constraint = new Constraint(
                "si",
                "no",
                "si",
                "",
                ""
        );
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_throws_02() {
        Constraint constraint = new Constraint(
                "ivan",
                "no",
                "si",
                "",
                ""
        );
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintExists(xmlStructure, "table_name_03", constraint));
    }

    @Test
    void constraintExists_throws_03() {
        Constraint constraint = new Constraint(
                "ivan",
                "no",
                "si",
                "",
                ""
        );
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintExists(xmlStructure, "waos", constraint));
    }

    @Test
    void constraintExists_throws_04() {
        Constraint constraint = new Constraint(
                "ivan",
                "no",
                "si",
                "",
                ""
        );
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintExists(xmlStructure, "aaa", constraint));
    }

    @Test
    void constraintExists_throws_05() {
        Constraint constraint = new Constraint(
                "no existo",
                "no",
                "si",
                "",
                ""
        );
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintExists(xmlStructure, "yo tampoco", constraint));
    }

    @Test
    void constraintNameExists_true_01() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintNameExists("table_name_02", "constraint_name_01_02", xmlStructure));
    }

    @Test
    void constraintNameExists_true_02() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintNameExists("table_name_02", "constraint_name_02_02", xmlStructure));
    }

    @Test
    void constraintNameExists_true_03() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintNameExists("table_name_03", "constraint_name_01_02", xmlStructure));
    }

    @Test
    void constraintNameExists_true_04() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintNameExists("table_name_04", "constraint_name_03_03", xmlStructure));
    }

    @Test
    void constraintNameExists_true_05() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintNameExists("table_name_05", "constraint_name_03_03", xmlStructure));
    }

    @Test
    void constraintNameExists_false_01() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintNameExists("table_name_05", "si", xmlStructure));
    }

    @Test
    void constraintNameExists_false_02() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintNameExists("table_name_05", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_false_03() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintNameExists("table_name_05", "preguntale al gobierno cuando", xmlStructure));
    }

    @Test
    void constraintNameExists_false_04() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintNameExists("table_name_05", "vi", xmlStructure));
    }

    @Test
    void constraintNameExists_false_05() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintNameExists("table_name_05", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_throws_01() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintNameExists("table_name_10", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_throws_02() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintNameExists("ivan", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_throws_03() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintNameExists("waos", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_throws_04() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintNameExists("otra tabla que no existe", "no", xmlStructure));
    }

    @Test
    void constraintNameExists_throws_05() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintNameExists("ya no quiero hacer mas tests", "no", xmlStructure));
    }

    @Test
    void constraintColumnExists_true_01() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintColumnExists("table_name_02", "constraint_type_01_02", "column_name_01_02", xmlStructure));
    }

    @Test
    void constraintColumnExists_true_02() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintColumnExists("table_name_02", "constraint_type_01_02", "column_name_01_02", xmlStructure));
    }

    @Test
    void constraintColumnExists_true_03() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintColumnExists("table_name_03", "constraint_type_02_02", "column_name_02_02", xmlStructure));
    }

    @Test
    void constraintColumnExists_true_04() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintColumnExists("table_name_03", "constraint_type_02_02", "column_name_02_02", xmlStructure));
    }

    @Test
    void constraintColumnExists_true_05() throws ElementDoesNotExistsException {
        assertTrue(ConstraintManagement.constraintColumnExists("table_name_04", "constraint_type_02_03", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_false_01() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintColumnExists("table_name_04", "no existo", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_false_02() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintColumnExists("table_name_04", "si?", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_false_03() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintColumnExists("table_name_04", "tampoco existo", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_false_04() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintColumnExists("table_name_04", "", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_false_05() throws ElementDoesNotExistsException {
        assertFalse(ConstraintManagement.constraintColumnExists("table_name_04", "primary-key-constraint", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_throws_01() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintColumnExists("table_name_04", "primary-key-constraint", "columnita", xmlStructure));
    }

    @Test
    void constraintColumnExists_throws_02() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintColumnExists("table_name_10", "primary-key-constraint", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_throws_03() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintColumnExists("users", "primary-key-constraint", "columnita", xmlStructure));
    }

    @Test
    void constraintColumnExists_throws_04() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintColumnExists("table_name_12", "primary-key-constraint", "column_name_02_03", xmlStructure));
    }

    @Test
    void constraintColumnExists_throws_05() {
        assertThrows(ElementDoesNotExistsException.class, () -> ConstraintManagement.constraintColumnExists("tablita", "primary-key-constraint", "columnita", xmlStructure));
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