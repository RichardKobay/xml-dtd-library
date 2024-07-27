package dev.soriane.dtdxmlparser.model.dtd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DTDElement {
    private String name;
    private List<Map<String, String>> childrenCardinalities;
    private List<DTDAttribute> attributes;
    private List<DTDElement> children;

    public DTDElement(String name) {
        this.name = name;
        this.childrenCardinalities = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String >> getChildrenCardinalities() {
        return childrenCardinalities;
    }

    public void setChildrenCardinalities(List<Map<String, String>> childrenCardinalities) {
        this.childrenCardinalities = childrenCardinalities;
    }

    public List<DTDAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DTDAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<DTDElement> getChildren() {
        return children;
    }

    public void setChildren(List<DTDElement> children) {
        this.children = children;
    }
}
