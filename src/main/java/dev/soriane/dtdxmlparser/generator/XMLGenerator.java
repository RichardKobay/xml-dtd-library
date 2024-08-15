package dev.soriane.dtdxmlparser.generator;

import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.dtd.DTDElement;
import dev.soriane.dtdxmlparser.model.dtd.DTDAttribute;
import dev.soriane.dtdxmlparser.exceptions.NeedChildElementException;
import dev.soriane.dtdxmlparser.model.xml.Attribute;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;

import java.util.Map;

public class XMLGenerator {

    public String generateEmptyXML(DTDStructure dtdStructure, String rootAttributeName) throws NeedChildElementException {
        if (dtdStructure == null || dtdStructure.getRootElement() == null) {
            throw new IllegalArgumentException("DTDStructure or root element is null");
        }

        DTDElement rootElement = dtdStructure.getRootElement();

        // Check if root element needs children
        for (Map<String, String> childCardinality : rootElement.getChildrenCardinalities()) {
            String cardinality = childCardinality.values().iterator().next();
            if ("+".equals(cardinality) || "".equals(cardinality)) {
                throw new NeedChildElementException("Root element requires child elements");
            }
        }

        // Start building the XML string
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");

        // Build the root element
        xmlBuilder.append("<").append(rootElement.getName());

        for (DTDAttribute attribute : rootElement.getAttributes()) {
            xmlBuilder.append(" ").append(attribute.getName()).append("=\"").append(rootAttributeName).append("\"");
        }

        xmlBuilder.append(">").append("\n</").append(rootElement.getName()).append(">");

        return xmlBuilder.toString();
    }

    public static String xmlStructureString(XMLStructure xmlStructure) {
        StringBuilder xmlString = new StringBuilder();
        xmlString.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
        buildXmlString(xmlStructure.getRootElement(), xmlString, 0);
        return xmlString.toString();
    }

    private static void buildXmlString(Element element, StringBuilder xmlString, int indent) {
        appendIndent(xmlString, indent);
        xmlString.append("<").append(element.getName());

        // Add attributes
        for (Attribute attribute : element.getAttributes()) {
            xmlString.append(" ").append(attribute.getName()).append("=\"").append(attribute.getValue()).append("\"");
        }

        if (element.getChildren().isEmpty() && element.getContent() == null) {
            xmlString.append(">\n");
            appendIndent(xmlString, indent);
            xmlString.append("</").append(element.getName()).append(">\n");
        } else {
            xmlString.append(">");

            if (element.getContent() != null) {
                xmlString.append(element.getContent());
            } else {
                xmlString.append("\n");
            }

            for (Element child : element.getChildren()) {
                buildXmlString(child, xmlString, indent + 1);
            }

            if (element.getContent() == null) {
                appendIndent(xmlString, indent);
            }
            xmlString.append("</").append(element.getName()).append(">\n");
        }
    }

    private static void appendIndent(StringBuilder xmlString, int indent) {
        for (int i = 0; i < indent; i++) {
            xmlString.append("    ");
        }
    }
}
