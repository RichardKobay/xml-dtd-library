package dev.soriane.dtdxmlparser.parser;

import dev.soriane.dtdxmlparser.model.xml.Attribute;
import dev.soriane.dtdxmlparser.model.xml.Element;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public XMLStructure parse(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));

        XMLStructure xmlStructure = new XMLStructure();
        Element rootElement = parseElement(document.getDocumentElement());
        xmlStructure.setRootElement(rootElement);

        return xmlStructure;
    }

    private Element parseElement(Node node) {
        Element element = new Element(node.getNodeName());

        // Set attributes
        List<Attribute> attributes = new ArrayList<>();
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attr = node.getAttributes().item(i);
                Attribute attribute = new Attribute(attr.getNodeName(), "CDATA", false);
                attribute.setValue(attr.getNodeValue());
                attributes.add(attribute);
            }
        }
        element.setAttributes(attributes);

        // Set children
        NodeList children = node.getChildNodes();
        List<Element> childElements = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                childElements.add(parseElement(child));
            } else if (child.getNodeType() == Node.TEXT_NODE && !child.getTextContent().trim().isEmpty()) {
                element.setContent(child.getTextContent().trim());
            }
        }
        element.setChildren(childElements);

        return element;
    }
}
