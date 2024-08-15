package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.*;
import dev.soriane.dtdxmlparser.model.xml.Attribute;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import dev.soriane.dtdxmlparser.model.xml.Constraint;

import java.util.LinkedHashMap;
import java.util.List;

public class XMLUtil {

    public static void addTable(XMLStructure xmlStructure, String tableName, LinkedHashMap<String, String> columns, List<Constraint> constraints) throws ElementAlreadyExistsException {
        try {
        if (findTableElement(xmlStructure, tableName) != null)
            throw new ElementAlreadyExistsException("The table already exists");
        } catch (ElementDoesNotExistsException ignored) {}

        Element database = xmlStructure.getRootElement();

        Element table = new Element("table");
        Attribute attribute = new Attribute("name", "CDATA", tableName);
        table.addAttribute(attribute);

        Element columnsElement = new Element("columns");
        for (String columnName : columns.keySet()) {
            String columnType = columns.get(columnName);
            Element columnElement = new Element("column");
            columnElement.setContent(columnName);
            columnElement.addAttribute(new Attribute("type", "CDATA", columnType));
            columnsElement.addChild(columnElement);
        }

        Element constraintsElement = new Element("constraints");

        for (Constraint constraint : constraints) {
            Element constraintElement = new Element("constraint");

            Element typeElement = new Element("type");
            typeElement.setContent(constraint.getConstraintType());
            constraintElement.addChild(typeElement);

            Element columnNameElement = new Element("columnname");
            columnNameElement.setContent(constraint.getColumnName());
            constraintElement.addChild(columnNameElement);

            Element nameElement = new Element("name");
            nameElement.setContent(constraint.getConstraintName());
            constraintElement.addChild(nameElement);

            Element tableReferenceElement = new Element("tablereference");
            tableReferenceElement.setContent(constraint.getTableReference());
            constraintElement.addChild(tableReferenceElement);

            Element columnReference = new Element("columnreference");
            columnReference.setContent(constraint.getColumnReference());
            constraintElement.addChild(columnReference);

            constraintsElement.addChild(constraintElement);
        }

        // Add columns and constraints to the table element
        table.addChild(columnsElement);
        table.addChild(constraintsElement);

        // Add the table element to the database element
        database.addChild(table);
    }

    public static void dropTable(XMLStructure xmlStructure, String tableName) throws ElementDoesNotExistsException, MalformedXMLException {
        if (!tableExists(xmlStructure, tableName))
            throw new ElementDoesNotExistsException("The xml table does not exists in the structure");

        Element table = findTableElement(xmlStructure, tableName);
        xmlStructure.getRootElement().getChildren().remove(table);
    }

    public static void addColumn(XMLStructure xmlStructure, String tableName, String columnName, String dataType) throws ElementDoesNotExistsException, ElementAlreadyExistsException, MalformedXMLException {
        if (ColumnManagement.columnExists(xmlStructure, tableName, columnName))
            throw new ElementAlreadyExistsException("The column already exists, please check your column name");

        Element table = findTableElement(xmlStructure, tableName);
        Element columnsElement = findOrCreateColumnsElement(table);
        Element columnElement = new Element("column");
        Attribute attribute = new Attribute("type", "CDATA", dataType);
        columnElement.setContent(columnName);
        columnElement.getAttributes().add(attribute);
        columnsElement.addChild(columnElement);
    }

    public static void editColumn (XMLStructure xmlStructure, String tableName, String columnName, String newColumnName, String newDataType) throws ElementDoesNotExistsException, MalformedXMLException {
        if (!ColumnManagement.columnExists(xmlStructure, tableName, columnName))
            throw new ElementDoesNotExistsException("The column does not exists, cannot drop an element that does not exists");

        Element table = findTableElement(xmlStructure, tableName);
        Element columnsElement = findOrCreateColumnsElement(table);

        for (Element column : columnsElement.getChildren()) {
            if (column.getContent().equals(columnName)) {
                column.getAttributes().get(0).setValue(newDataType);
                column.setContent(newColumnName);
            }
        }
    }

    public static void dropColumn (XMLStructure xmlStructure, String tableName, String columnName) throws ElementDoesNotExistsException, MalformedXMLException {
        if (!ColumnManagement.columnExists(xmlStructure, tableName, columnName))
            throw new ElementDoesNotExistsException("The column does not exists, cannot drop an element that does not exists");

        Element table = findTableElement(xmlStructure, tableName);
        Element columnsElement = findOrCreateColumnsElement(table);

        for (Element column : columnsElement.getChildren()) {
            if (column.getContent().equals(columnName)) {
                columnsElement.getChildren().remove(column);
                return;
            }
        }

        List<Constraint> constraints = ConstraintManagement.getColumnConstraints(tableName, columnName, xmlStructure);
        for (Constraint constraint : constraints) {
            dropConstraint(xmlStructure, tableName, constraint.getConstraintName());
        }
    }

    public static void addConstraint(XMLStructure xmlStructure, String tableName, Constraint constraint) throws MalformedXMLException, ElementDoesNotExistsException, ElementAlreadyExistsException {
        if (ConstraintManagement.constraintExists(xmlStructure, tableName, constraint))
            throw new ElementAlreadyExistsException("This constraint already exists. This may be caused by a same constraint type in the same column or repeated constraint name");

        if (!ColumnManagement.columnExists(xmlStructure, tableName, constraint.getColumnName()))
            throw new ElementDoesNotExistsException("The column of the constraint does not exists");

        if (!constraint.getTableReference().isEmpty() || !constraint.getColumnReference().isEmpty()) {
            Element table = findTableElement(xmlStructure, tableName);
            if (!ColumnManagement.columnExists(xmlStructure, constraint.getTableReference(), constraint.getColumnReference()))
                throw new ElementDoesNotExistsException("The column reference does not exists");
        }

        Element table = findTableElement(xmlStructure, tableName);
        List<Element> children = table.getChildren();

        if (children.isEmpty())
            throw new MalformedXMLException("The xml table has no children, this can be caused by a manual manipulation of the file");

        if (children.size() < 2)
            throw new MalformedXMLException("The xml table has no <constraints> element, this can be caused by a manual manipulation of the file");

        Element constraints = findOrCreateConstraintsElement(table);
        Element constraintElement = new Element("constraint");

        Element type = new Element("type", constraint.getConstraintType());
        Element columnName = new Element("columnname", constraint.getColumnName());
        Element name = new Element("name", constraint.getConstraintName());
        Element tableReference = new Element("tablereference", constraint.getTableReference());
        Element columnReference = new Element("columnreference", constraint.getColumnReference());

        constraintElement.addChild(type);
        constraintElement.addChild(columnName);
        constraintElement.addChild(name);
        constraintElement.addChild(tableReference);
        constraintElement.addChild(columnReference);

        constraints.addChild(constraintElement);
    }


    public static void dropConstraint(XMLStructure xmlStructure, String tableName, String constraintName) throws ElementDoesNotExistsException {
        if (!ConstraintManagement.constraintNameExists(tableName, constraintName, xmlStructure))
            throw new ElementDoesNotExistsException("Constraint does not exists. Cannot drop an un-existing item");

        Element table = findTableElement(xmlStructure, tableName);
        Element constraints = findOrCreateConstraintsElement(table);

        for (Element constraintElement : constraints.getChildren()) {
            for (Element constraintParameter : constraintElement.getChildren()) {
                if (constraintParameter.getName().equals(constraintName))
                    constraints.getChildren().remove(constraintElement);
            }
        }
    }

    private static Element findOrCreateConstraintsElement(Element table) {
        for (Element child : table.getChildren()) {
            if (child.getName().equals("constraints")) {
                return child;
            }
        }
        Element constraintsElement = new Element("constraints");
        table.addChild(constraintsElement);
        return constraintsElement;
    }

    private static Element findOrCreateColumnsElement(Element table) {
        for (Element child : table.getChildren()) {
            if (child.getName().equals("columns")) {
                return child;
            }
        }

        Element columnsElement = new Element("columns");
        table.getChildren().add(columnsElement);
        return columnsElement;
    }


    protected static boolean tableExists(XMLStructure xmlStructure, String tableName) throws MalformedXMLException {
        for (Element child : xmlStructure.getRootElement().getChildren()) {
            if (!child.getName().equals("table"))
                throw new MalformedXMLException("The XML has not the right format. This can be caused by a manual manipulation of the files");

            for (Attribute attribute : child.getAttributes()) {
                if (attribute.getName().equals("name") && attribute.getValue().equals(tableName))
                    return true;
            }
        }
        return false;
    }

    private static Element findTableElement(XMLStructure xmlStructure, String tableName) throws ElementDoesNotExistsException {
        Element database = xmlStructure.getRootElement();
        List<Element> children = database.getChildren();

        for (Element table : children) {
            for (Attribute attribute : table.getAttributes()) {
                if (attribute.getName().equals("name") && attribute.getValue().equals(tableName)) {
                    return table;
                }
            }
        }

        throw new ElementDoesNotExistsException("The table does not exists in the XML, please check your table name");
    }
}
