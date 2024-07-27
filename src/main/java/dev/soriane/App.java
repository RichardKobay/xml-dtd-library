package dev.soriane;

import dev.soriane.dtdxmlparser.model.dtd.DTDStructure;
import dev.soriane.dtdxmlparser.model.xml.XMLStructure;
import dev.soriane.dtdxmlparser.parser.DTDParser;
import dev.soriane.dtdxmlparser.parser.XMLParser;
import dev.soriane.dtdxmlparser.validator.DTDValidator;
import dev.soriane.plibs.file.FileManager;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String xml = FileManager.readFile("C:\\Users\\ricar\\dev\\poo\\dtd-xml-parser\\src\\main\\resources\\document.xml");
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

        System.out.println("ivan");
        System.out.println("ivan");
        System.out.println("ivan");
    }
}
