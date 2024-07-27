package dev.soriane.dtdxmlparser.generator;

import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.dtd.DTDElement;
import dev.soriane.dtdxmlparser.model.dtd.DTDAttribute;
import dev.soriane.dtdxmlparser.exceptions.NeedChildElementException;

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
}
