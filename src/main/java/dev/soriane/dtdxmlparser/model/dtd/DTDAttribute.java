package dev.soriane.dtdxmlparser.model.dtd;

public class DTDAttribute {
    private String name;
    private String type;
    private boolean required;

    public DTDAttribute(String name, String type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }
}
