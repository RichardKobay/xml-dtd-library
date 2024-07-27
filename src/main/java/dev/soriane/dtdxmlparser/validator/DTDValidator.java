package dev.soriane.dtdxmlparser.validator;

import dev.soriane.dtdxmlparser.model.dtd.DTDAttribute;
import dev.soriane.dtdxmlparser.model.dtd.DTDElement;
import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DTDValidator {

    public boolean validate(XMLStructure xmlStructure, DTDStructure dtdStructure) {
        return validateElement(xmlStructure.getRootElement(), dtdStructure.getRootElement());
    }

    private boolean validateElement(Element xmlElement, DTDElement dtdElement) {
        // Validate element name
        if (!xmlElement.getName().equals(dtdElement.getName())) {
            return false;
        }

        // Validate attributes
        if (!validateAttributes(xmlElement, dtdElement)) {
            return false;
        }

        // Validate children and cardinalities
        if (!validateChildren(xmlElement, dtdElement)) {
            return false;
        }

        return true;
    }

    private boolean validateAttributes(Element xmlElement, DTDElement dtdElement) {
        Map<String, DTDAttribute> dtdAttributesMap = dtdElement.getAttributes().stream()
                .collect(Collectors.toMap(DTDAttribute::getName, attr -> attr));

        for (DTDAttribute dtdAttribute : dtdElement.getAttributes()) {
            String attrName = dtdAttribute.getName();
            boolean required = dtdAttribute.isRequired();
            boolean found = xmlElement.getAttributes().stream()
                    .anyMatch(xmlAttr -> xmlAttr.getName().equals(attrName));

            if (required && !found) {
                return false;
            }
        }

        return true;
    }

    private boolean validateChildren(Element xmlElement, DTDElement dtdElement) {
        Map<String, String> dtdChildrenCardinalities = new HashMap<>();
        for (Map<String, String> childCardinality : dtdElement.getChildrenCardinalities()) {
            dtdChildrenCardinalities.putAll(childCardinality);
        }

        Map<String, Integer> xmlChildrenCounts = new HashMap<>();
        for (Element child : xmlElement.getChildren()) {
            xmlChildrenCounts.put(child.getName(), xmlChildrenCounts.getOrDefault(child.getName(), 0) + 1);
        }

        for (DTDElement dtdChild : dtdElement.getChildren()) {
            String childName = dtdChild.getName();
            String cardinality = dtdChildrenCardinalities.get(childName);
            int count = xmlChildrenCounts.getOrDefault(childName, 0);

            if (!validateCardinality(cardinality, count)) {
                return false;
            }

            // Validate each child element recursively
            for (Element xmlChild : xmlElement.getChildren()) {
                if (xmlChild.getName().equals(childName) && !validateElement(xmlChild, dtdChild)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validateCardinality(String cardinality, int count) {
        switch (cardinality) {
            case "*":
                return true;
            case "+":
                return count > 0;
            case "?":
                return count <= 1;
            case "":
                return count == 1;
        }
        return false;
    }
}
