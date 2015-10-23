package psyco.coder.component.jdbc;


import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class JdbcType {

    public static ImmutableMap<String, String> typeMap = ImmutableMap.<String, String>builder()
            .put("VARCHAR", "String")
            .put("CHAR", "String")
            .put("LONGVARCHAR", "String")
            .put("BIT", "Boolean")
            .put("NUMERIC", "java.math.BigDecimal")
            .put("TINYINT", "Byte")
            .put("SMALLINT", "Short")
            .put("INTEGER", "Int")
            .put("BIGINT", "Long")
            .put("REAL", "Float")
            .put("FLOAT", "Float")
            .put("DOUBLE", "Double")
            .put("VARBINARY", "byte[]")
            .put("BINARY", "byte[]")
            .put("DATE", "java.sql.Date")
            .put("TIME", "java.sql.Time")
            .put("TIMESTAMP", "java.sql.Timestamp")
            .put("CLOB", "java.sql.Clob")
            .put("BLOB", "java.sql.Blob")
            .put("ARRAY", "java.sql.Array")
            .put("REF", "java.sql.Ref")
            .put("STRUCT", "java.sql.Struct")
            .put("INT", "Integer")
            .put("DATETIME", "Date")
            .build();

    public static String getJavaType(String jdbcType) {
        /** filter "INT UNSIGNED" => "INT" */
        Matcher m = Pattern.compile("^(\\S+)\\s*").matcher(jdbcType);
        if (m.find())
            jdbcType = typeMap.get(m.group(1));
        if (StringUtils.isBlank(jdbcType))
            throw new RuntimeException("Unknown type for:" + jdbcType);
        return jdbcType;
    }

    public static List<TableInfo> fromJDBCInfo(JdbcExecutor jdbc) throws Exception {
        jdbc.init();
        return jdbc.getTables().entrySet().stream().map(en ->
                new TableInfo(
                        en.getKey(),
                        en.getValue().stream()
                                .map(col -> {
                                    String columnName = col.get(0);
                                    String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                                    String columnType = col.get(1);
                                    String columnJavaType = getJavaType(columnType);
                                    int columnSize = Integer.parseInt(col.get(2));
                                    boolean isPrimaryKey = Boolean.parseBoolean(col.get(3));
                                    return new ColumnInfo(columnName, fieldName, columnType, columnJavaType, columnSize, isPrimaryKey);
                                }).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

}
