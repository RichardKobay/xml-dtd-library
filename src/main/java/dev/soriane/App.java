package dev.soriane;

import dev.soriane.dtdxmlparser.exceptions.NeedChildElementException;
import dev.soriane.dtdxmlparser.exceptions.XMLColumnDoesNotExistsException;
import dev.soriane.dtdxmlparser.exceptions.XMLConstraintDoesNotExistsException;
import dev.soriane.dtdxmlparser.exceptions.XMLTableDoesNotExistsException;
import dev.soriane.dtdxmlparser.generator.XMLGenerator;
import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.Constraint;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import dev.soriane.dtdxmlparser.parser.DTDParser;
import dev.soriane.dtdxmlparser.parser.XMLParser;
import dev.soriane.dtdxmlparser.utils.XMLUtil;
import dev.soriane.dtdxmlparser.validator.DTDValidator;
import dev.soriane.plibs.file.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, XMLConstraintDoesNotExistsException {
        /*String xml = FileManager.readFile("C:\\Users\\ricar\\dev\\poo\\dtd-xml-parser\\src\\main\\resources\\document.xml");
        String dtd = FileManager.readFile("C:\\Users\\ricar\\dev\\poo\\dtd-xml-parser\\src\\main\\resources\\file.dtd");

        XMLParser xmlParser = new XMLParser();
        DTDParser dtdParser = new DTDParser();
        XMLStructure xmlStructure = new XMLStructure();
        DTDStructure dtdStructure = new DTDStructure();

        try {
            xmlStructure = xmlParser.parse(xml);
            dtdStructure = dtdParser.parse(dtd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        DTDValidator validator = new DTDValidator();
        System.out.println(validator.validate(xmlStructure, dtdStructure));

        // Adding a table to the xmlStructure

        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("column_1", "varchar");
        columns.put("column_2", "varchar");
        columns.put("column_3", "varchar");
        Constraint constraint = new Constraint("column-name", "constraint-type", "name");
        List<Constraint> constraints = new ArrayList<>();
        constraints.add(constraint);
        XMLUtil.addTable(xmlStructure, "ivan", columns, constraints);

        // Other table to delete after

        columns = new LinkedHashMap<>();
        columns.put("column_1", "varchar");
        columns.put("column_2", "varchar");
        columns.put("column_3", "varchar");
        constraint = new Constraint("ivan", "constraint-type", "name");
        constraints = new ArrayList<>();
        constraints.add(constraint);
        XMLUtil.addTable(xmlStructure, "jesus", columns, constraints);

        columns = new LinkedHashMap<>();
        columns.put("column_1", "varchar");
        columns.put("column_2", "varchar");
        columns.put("column_3", "varchar");
        constraint = new Constraint("ivan", "constraint-type", "name");
        constraints = new ArrayList<>();
//        constraints.add(constraint);
        XMLUtil.addTable(xmlStructure, "mitza", columns, constraints);


        // Deleting a table
        try {
            XMLUtil.removeTable(xmlStructure, "jesus");
        } catch (XMLTableDoesNotExistsException e) {
            System.err.println(e.getMessage());
        }

        // Adding a column into ivan
        XMLUtil.addColumn(xmlStructure, "ivan", "columnita", "varchar");

        // Editing an existing column
        try {
            XMLUtil.editColumn(xmlStructure, "mitza", "column_2", "columnita_2", "number");
        } catch (XMLColumnDoesNotExistsException e) {
            System.err.println(e.getMessage());
        }

        // Dropping a column
        try {
            XMLUtil.dropColumn(xmlStructure, "ivan", "column_3");
        } catch (XMLColumnDoesNotExistsException e) {
            System.err.println(e.getMessage());
        }

        // Adding a constraint
        Constraint constraint_1 = new Constraint("columnita", "unique-constraint", "soy-una-constraint-1");
        Constraint constraint_2 = new Constraint("columnita", "primary-key", "soy-una-constraint-2");
        Constraint constraint_3 = new Constraint("non-existing", "foreign-key", "soy-una-constraint-3");
        Constraint constraint_4 = new Constraint("another", "unique-constraint", "soy-una-constraint-4");
        XMLUtil.addConstraint(xmlStructure, "ivan", constraint_1);
        XMLUtil.addConstraint(xmlStructure, "ivan", constraint_2);
        XMLUtil.addConstraint(xmlStructure, "ivan", constraint_3);
        XMLUtil.addConstraint(xmlStructure, "ivan", constraint_4);

        // Deleting a constraint
        XMLUtil.deleteConstraint(xmlStructure, "ivan", "soy-una-constraint-4");

        // Generate an empty xml
        XMLGenerator xmlGenerator = new XMLGenerator();
        try {
            String emptyXML = xmlGenerator.generateEmptyXML(dtdStructure, "database-name");
            FileManager.rewriteFile("C:\\Users\\ricar\\dev\\poo\\dtd-xml-parser\\src\\main\\resources\\emptyxml.xml", emptyXML);
        } catch (NeedChildElementException e) {
            throw new RuntimeException(e);
        }


        FileManager.rewriteFile("C:\\Users\\ricar\\dev\\poo\\dtd-xml-parser\\src\\main\\resources\\newxml.xml", XMLGenerator.xmlStructureString(xmlStructure));
        System.out.println("ivan");
        System.out.println("ivan");
        System.out.println("ivan");*/
    }
}
