package psyco.coder.db.jdbc;

/**
 * Created by peng on 15/10/10.
 */
public class ColumnInfo {

    String columnName;
    String fieldName;
    String dbType;
    String javaType;
    int columnSize;
    boolean isPrimaryKey;

    public ColumnInfo(String columnName, String fieldName, String dbType, String javaType, int columnSize, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dbType = dbType;
        this.javaType = javaType;
        this.columnSize = columnSize;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnName='" + columnName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", dbType='" + dbType + '\'' +
                ", javaType='" + javaType + '\'' +
                ", columnSize=" + columnSize +
                ", isPrimaryKey=" + isPrimaryKey +
                '}';
    }
}
