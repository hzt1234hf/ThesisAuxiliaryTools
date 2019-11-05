package com.paper.auxiliary.entity;

public class Table_Info {
    private String columnName;
    private String columnComment;
    private Boolean isNullable;
    private String foreignKey;

    private String varName;
    private String varValue;



    public Table_Info() {
    }

    public Table_Info(String columnName, String columnComment, Boolean isNullable) {
        this.columnName = columnName;
        this.columnComment = columnComment;
        this.isNullable = isNullable;
        this.foreignKey = "";
    }

    @Override
    public String toString() {
        return "Table_Info{" +
                "columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", isNullable=" + isNullable +
                '}';
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }
}
