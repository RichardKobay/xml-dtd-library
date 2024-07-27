package dev.soriane.dtdxmlparser.model.xml;

public class XMLStructure {
    /* *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     *                   ATTRIBUTES                  *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     * */
    private Element rootElement;

    /* *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     *                   CONSTRUCTOR                 *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     * */
    public XMLStructure() {}

    /* *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     *              GETTERS AND SETTERS              *
     * * * * * * * * * * * * * * * * * * * * * * * * *
     * */
    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }
}
