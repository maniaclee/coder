package psyco.coder.db.jdbc;

import java.util.List;

/**
 * Created by peng on 15/10/10.
 */
public class TableInfo {
    String name;
    String className;
    List<ColumnInfo> columns;

    public TableInfo(String name, String className, List<ColumnInfo> columns) {
        this.name = name;
        this.className = className;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", columns=" + columns +
                '}';
    }
}
