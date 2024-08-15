package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.ElementDoesNotExistsException;
import dev.soriane.dtdxmlparser.model.xml.Attribute;
import dev.soriane.dtdxmlparser.model.xml.Constraint;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;

import java.util.ArrayList;
import java.util.List;

public class ConstraintManagement {
    public static List<Constraint> getTableConstraints(String tableName, XMLStructure xmlStructure) throws ElementDoesNotExistsException {
        if (!tableExists(xmlStructure, tableName))
            throw new ElementDoesNotExistsException("Cannot retrieve constraints of something that does not exists (table)");

        Element rootElement = xmlStructure.getRootElement();
        List<Constraint> constraints = new ArrayList<>();

        findTableConstraints(rootElement, tableName, constraints);

        return constraints;
    }

    public static List<Constraint> getColumnConstraints(String tableName, String columnName, XMLStructure xmlStructure) throws ElementDoesNotExistsException {
        if (!tableExists(xmlStructure, tableName))
            throw new ElementDoesNotExistsException("Cannot retrieve constraints of something that does not exists (table)");

        if (!columnExists(xmlStructure, tableName, columnName))
            throw new ElementDoesNotExistsException("Cannot retrieve constraints of something that does not exists (column)");

        List<Constraint> constraints = getTableConstraints(tableName, xmlStructure);
        constraints.removeIf(constraint -> !constraint.getColumnName().equals(columnName));
        return constraints;
    }

    public static boolean constraintExists(XMLStructure xmlStructure, String tableName, Constraint constraint) throws ElementDoesNotExistsException {
        String constraintName = constraint.getConstraintName();
        String columnName = constraint.getColumnName();
        String constraintType = constraint.getConstraintType();

        return ((constraintColumnExists(tableName, constraintType, columnName, xmlStructure)) || (constraintNameExists(tableName, constraintName, xmlStructure)));
    }

    public static boolean constraintNameExists(String tableName, String constraintName, XMLStructure xmlStructure) throws ElementDoesNotExistsException {
        List<Constraint> constraints = getTableConstraints(tableName, xmlStructure);
        return constraints.stream().anyMatch(constraint -> constraint.getConstraintName().equals(constraintName));
    }

    public static boolean constraintColumnExists(String tableName, String constraintType, String columnName, XMLStructure xmlStructure) throws ElementDoesNotExistsException {
        List<Constraint> constraints = getColumnConstraints(tableName, columnName, xmlStructure);
        return constraints.stream().anyMatch(constraint -> constraint.getConstraintType().equals(constraintType));
    }

    private static void findTableConstraints(Element element, String tableName, List<Constraint> constraints) {
        if (!element.getName().equals("table")) {
            for (Element child : element.getChildren())
                findTableConstraints(child, tableName, constraints);
            return;
        }

        if (element.getAttributes() == null)
            return;

        if (!element.getAttributes().get(0).getValue().equals(tableName))
            return;

        List<Element> children = element.getChildren();

        for (Element child : children) {
            if (!child.getName().equals("constraints"))
                continue;

            for (Element constraintElement : child.getChildren()) {
                String type = constraintElement.getChildren().get(0).getContent();
                String columnName = constraintElement.getChildren().get(1).getContent();
                String name = constraintElement.getChildren().get(2).getContent();
                String tableReference = constraintElement.getChildren().get(3).getContent();
                String columnReference = constraintElement.getChildren().get(4).getContent();
                Constraint constraint = new Constraint(
                        columnName,
                        type,
                        name,
                        tableReference,
                        columnReference
                );
                constraints.add(constraint);
            }
        }
    }

    private static boolean tableExists(XMLStructure xmlStructure, String tableName) {
        Element database = xmlStructure.getRootElement();
        List<Element> children = database.getChildren();

        for (Element table : children) {
            for (Attribute attribute : table.getAttributes()) {
                if (attribute.getName().equals("name") && attribute.getValue().equals(tableName)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean columnExists(XMLStructure xmlStructure, String tableName, String columnName) {
        Element database = xmlStructure.getRootElement();

        for (Element table : database.getChildren()) {
            for (Attribute attribute : table.getAttributes()) {
                if (!(attribute.getName().equals("name") && attribute.getValue().equals(tableName)))
                    continue;

                Element columnsElement = table.getChildren().get(0);
                for (Element column : columnsElement.getChildren()) {
                    if (column.getContent().equals(columnName))
                        return true;
                }
            }
        }

        return false;
    }
}
