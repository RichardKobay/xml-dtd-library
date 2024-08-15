package dev.soriane.dtdxmlparser.model.xml;

import java.util.ArrayList;
import java.util.List;

public class Element {
    /* *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *                   ATTRIBUTES                  *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * */

    private String name;
    private List<Element> children;
    private List<Attribute> attributes;
    private String content;

    /* *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *                  CONSTRUCTOR                  *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * */

    public Element (String name) {
        this.name = name;
        this.content = null;
        this.children = new ArrayList<Element>();
        this.attributes = new ArrayList<>();
    }

    public Element (String name, String content) {
        this.name = name;
        this.content = content;
        this.children = new ArrayList<Element>();
        this.attributes = new ArrayList<>();
    }

    /* *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *              GETTERS AND SETTERS              *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addAttribute (Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void addChild (Element child) {
        this.children.add(child);
    }
}
