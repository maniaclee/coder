package psyco.coder.component.jdbc;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by lipeng on 15/8/25.
 */
public class JdbcExecutor {


    public Connection connection;
    public DatabaseMetaData databaseMetaData;
    public JdbcConfig jdbcConfig;

    public JdbcExecutor(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }

    public void init() throws Exception {
        connection = getConnection(jdbcConfig.getUrl(), jdbcConfig.getUser(), jdbcConfig.getPassword());
        databaseMetaData = connection.getMetaData();
    }


    private Connection getConnection(String URL, String USER, String PASS) throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


    public List<String> getTablesNames() {
        List<String> re = new LinkedList<>();
        try {
            ResultSet result = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (result.next()) {
                re.add(result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return re;
    }

    public List<List<String>> getTable(String table) {
        List<List<String>> re = Lists.newLinkedList();
        try {
            ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, table);
            String pk = primaryKeys.next() ? primaryKeys.getString("COLUMN_NAME") : null;
            ResultSet resultSet = databaseMetaData.getColumns(null, null, table, null);
            while (resultSet.next()) {
                re.add(Lists.newArrayList(
                        resultSet.getString("COLUMN_NAME"),
                        resultSet.getString("TYPE_NAME"),
                        String.valueOf(resultSet.getInt("COLUMN_SIZE")),
                        String.valueOf(Objects.equals(pk, resultSet.getString("COLUMN_NAME"))),
                        /*not used*/
                        String.valueOf(resultSet.getInt("NULLABLE")),
                        resultSet.getString("TABLE_SCHEM")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return re;
    }

    public Map<String, List<List<String>>> getTables() {
        return getTablesNames().stream().collect(Collectors.toMap((String t) -> t, (String e) -> getTable(e)));
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("close jdbc connection ....");
        close();
        super.finalize();
    }


    public List<TableInfo> jdbcTables() throws Exception {
        return getTables().entrySet().stream().map(en ->
                new TableInfo(
                        en.getKey(),
                        en.getValue().stream()
                                .map(col -> {
                                    String columnName = col.get(0);
                                    String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                                    String columnType = col.get(1);
                                    String columnJavaType = JdbcType.getJavaType(columnType);
                                    int columnSize = Integer.parseInt(col.get(2));
                                    boolean isPrimaryKey = Boolean.parseBoolean(col.get(3));
                                    return new ColumnInfo(columnName, fieldName, columnType, columnJavaType, columnSize, isPrimaryKey);
                                }).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

}
