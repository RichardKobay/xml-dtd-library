package dev.soriane.dtdxmlparser.model.xml;

public class Attribute {
    /* *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *                   ATTRIBUTES                  *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * */

    private String name;
    private String type;
    private String value;

    /* *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    *                   CONSTRUCTOR                 *
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * */

    public Attribute (String name, String type, boolean isRequired) {
        this.name = name;
        this.type = type;
    }

    public Attribute (String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
