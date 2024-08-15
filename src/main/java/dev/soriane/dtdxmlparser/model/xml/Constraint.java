package dev.soriane.dtdxmlparser.model.xml;

public class Constraint {
    private String columnName;
    private String constraintType;
    private String constraintName;
    private String tableReference;
    private String columnReference;

    public Constraint(String columnName, String constraintType, String constraintName) {
        this.columnName = columnName;
        this.constraintType = constraintType;
        this.constraintName = constraintName;
        this.tableReference = "";
        this.columnReference = "";
    }

    public Constraint(String columnName, String constraintType, String constraintName, String tableReference, String columnReference) {
        this.columnName = columnName;
        this.constraintType = constraintType;
        this.constraintName = constraintName;
        this.tableReference = tableReference;
        this.columnReference = columnReference;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getTableReference() {
        return tableReference;
    }

    public void setTableReference(String tableReference) {
        this.tableReference = tableReference;
    }

    public String getColumnReference() {
        return columnReference;
    }

    public void setColumnReference(String columnReference) {
        this.columnReference = columnReference;
    }
}
