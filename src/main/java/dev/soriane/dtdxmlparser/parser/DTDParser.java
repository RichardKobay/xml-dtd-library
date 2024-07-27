package dev.soriane.dtdxmlparser.parser;

import dev.soriane.dtdxmlparser.model.dtd.DTDAttribute;
import dev.soriane.dtdxmlparser.model.dtd.DTDElement;
import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DTDParser {

    private static final Pattern ELEMENT_PATTERN = Pattern.compile("<!ELEMENT\\s+(\\w+)\\s*\\(([^)]*)\\)>");
    private static final Pattern ATTLIST_PATTERN = Pattern.compile("<!ATTLIST\\s+(\\w+)\\s+(\\w+)\\s+CDATA\\s+#(REQUIRED|IMPLIED)>");

    public DTDStructure parse(String dtdContent) {
        DTDStructure dtdStructure = new DTDStructure();
        Map<String, DTDElement> elementsMap = new HashMap<>();

        parseElements(dtdContent, elementsMap);
        parseAttributes(dtdContent, elementsMap);
        linkChildren(elementsMap);

        DTDElement rootElement = findRootElement(elementsMap);
        dtdStructure.setRootElement(rootElement);

        return dtdStructure;
    }

    private void parseElements(String dtdContent, Map<String, DTDElement> elementsMap) {
        Matcher elementMatcher = ELEMENT_PATTERN.matcher(dtdContent);
        while (elementMatcher.find()) {
            String elementName = elementMatcher.group(1);
            String children = elementMatcher.group(2).trim();

            DTDElement element = new DTDElement(elementName);
            List<Map<String, String>> childrenCardinalities = parseChildrenCardinalities(children);
            element.setChildrenCardinalities(childrenCardinalities);

            elementsMap.put(elementName, element);
        }
    }

    private List<Map<String, String>> parseChildrenCardinalities(String children) {
        List<Map<String, String>> childrenCardinalities = new ArrayList<>();
        if (!children.isEmpty()) {
            String[] childrenArray = children.split(",\\s*");
            for (String child : childrenArray) {
                String childName = child.replaceAll("[*+?]", "").trim();
                String cardinality = child.replaceAll("\\w+", "").trim();
                childrenCardinalities.add(Map.of(childName, cardinality));
            }
        }
        return childrenCardinalities;
    }

    private void parseAttributes(String dtdContent, Map<String, DTDElement> elementsMap) {
        Matcher attlistMatcher = ATTLIST_PATTERN.matcher(dtdContent);
        while (attlistMatcher.find()) {
            String elementName = attlistMatcher.group(1);
            String attributeName = attlistMatcher.group(2);
            boolean required = attlistMatcher.group(3).equals("REQUIRED");

            DTDAttribute attribute = new DTDAttribute(attributeName, "CDATA", required);
            if (elementsMap.containsKey(elementName)) {
                elementsMap.get(elementName).getAttributes().add(attribute);
            }
        }
    }

    private void linkChildren(Map<String, DTDElement> elementsMap) {
        for (DTDElement element : elementsMap.values()) {
            List<DTDElement> children = new ArrayList<>();
            List<Map<String, String>> validChildrenCardinalities = new ArrayList<>();
            for (Map<String, String> childCardinality : element.getChildrenCardinalities()) {
                for (String childName : childCardinality.keySet()) {
                    if (elementsMap.containsKey(childName)) {
                        children.add(elementsMap.get(childName));
                        validChildrenCardinalities.add(childCardinality);
                    }
                }
            }
            element.setChildren(children);
            if (children.isEmpty()) {
                element.setChildrenCardinalities(new ArrayList<>()); // Clear childrenCardinalities for final elements
            } else {
                element.setChildrenCardinalities(validChildrenCardinalities); // Keep only valid childrenCardinalities
            }
        }
    }

    private DTDElement findRootElement(Map<String, DTDElement> elementsMap) {
        Set<String> allChildrenNames = new HashSet<>();
        for (DTDElement element : elementsMap.values()) {
            for (Map<String, String> childCardinality : element.getChildrenCardinalities()) {
                allChildrenNames.addAll(childCardinality.keySet());
            }
        }
        for (String elementName : elementsMap.keySet()) {
            if (!allChildrenNames.contains(elementName)) {
                return elementsMap.get(elementName);
            }
        }
        return null; // Handle case where root element is not found (should not occur with valid DTD)
    }
}
