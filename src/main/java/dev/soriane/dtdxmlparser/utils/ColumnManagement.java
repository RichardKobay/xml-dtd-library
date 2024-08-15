package dev.soriane.dtdxmlparser.utils;

import dev.soriane.dtdxmlparser.exceptions.ElementDoesNotExistsException;
import dev.soriane.dtdxmlparser.exceptions.MalformedXMLException;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;

import java.util.HashMap;

public class ColumnManagement {
    public static boolean columnExists(XMLStructure xmlStructure, String tableName, String columnName) throws MalformedXMLException, ElementDoesNotExistsException {
        return getTableColumns(xmlStructure, tableName).containsKey(columnName);
    }

    public static HashMap<String, String> getTableColumns(XMLStructure xmlStructure, String tableName) throws MalformedXMLException, ElementDoesNotExistsException {
        if (!XMLUtil.tableExists(xmlStructure, tableName))
            throw new ElementDoesNotExistsException("Cannot retrieve columns of a table that does not exists, please don't be stupid");

        HashMap<String, String> tableColumns = new HashMap<>();
        Element database = xmlStructure.getRootElement();

        for (Element table : database.getChildren()) {
            if (!table.getAttributes().get(0).getValue().equals(tableName))
                continue;

            Element columns = table.getChildren().get(0);
            for (Element column : columns.getChildren()) {
                tableColumns.put(column.getContent(), column.getAttributes().get(0).getValue());
            }
        }

        return tableColumns;
    }
}
