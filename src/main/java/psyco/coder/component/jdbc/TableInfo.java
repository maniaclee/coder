package psyco.coder.component.jdbc;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import psyco.coder.component.Base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by peng on 15/10/10.
 */
public class TableInfo extends Base implements Serializable {
    String name;
    List<ColumnInfo> columns;
    ColumnInfo primaryKey;
    String className;
    String classNameLower;
    String pack;

    public TableInfo(String name, List<ColumnInfo> columns) {
        this.name = name;
        this.className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
        this.classNameLower = StringUtils.uncapitalize(this.className);
        this.columns = columns;
        this.primaryKey = columns.stream().filter(columnInfo -> columnInfo.isPrimaryKey()).findFirst().get();
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

    public ColumnInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnInfo primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getClassNameLower() {
        return classNameLower;
    }

    public void setClassNameLower(String classNameLower) {
        this.classNameLower = classNameLower;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
