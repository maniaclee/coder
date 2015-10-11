package psyco.coder.db.jdbc;

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
public class JDBCInfo {


    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    public Connection connection;
    public DatabaseMetaData databaseMetaData;

    public JDBCInfo(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.dbUrl = DB_URL;
        this.dbUser = DB_USER;
        this.dbPassword = DB_PASSWORD;
    }

    public void init() throws Exception {
        connection = getConnection(dbUrl, dbUser, dbPassword);
        databaseMetaData = connection.getMetaData();
    }


    Connection getConnection(String URL, String USER, String PASS) throws SQLException {
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


}
