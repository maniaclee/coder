package psyco.coder.db.jdbc;

/**
 * Created by peng on 15/10/10.
 */
public class ColumnInfo {

    String columnName;
    String fieldName;
    String dbType;
    String javaTyp;
    int columnSize;
    boolean isPrimaryKey;

    public ColumnInfo(String columnName, String fieldName, String dbType, String javaTyp, int columnSize, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dbType = dbType;
        this.javaTyp = javaTyp;
        this.columnSize = columnSize;
        this.isPrimaryKey = isPrimaryKey;
    }

}
