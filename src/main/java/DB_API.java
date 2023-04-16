import java.sql.*;

public class DB_API {
    String url;
    String databaseName;
    String username;
    String password;

    private final int port = 3306;

    private Statement statement = null;

    public static DB_API api;

    // db api is a singleton class
    public static DB_API getAPI(String url, String databaseName, String username, String password) {
        if (api == null) {
            api = new DB_API(url, databaseName, username, password);
        }
        return api;
    }

    private DB_API(String url, String databaseName, String username, String password) {
        this.url = url;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
    }

    public Statement getStatement() throws SQLException {
        statement = null;
        return getConnection().createStatement();
    }

    public int executeUpdate(String query) throws SQLException {
        statement = null;
        return getStatement().executeUpdate(query);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        statement = null;
        return getStatement().executeQuery(query);
    }

    // returns true if there is a resultSet (meaning that execute query was executed)
    public boolean executeRaw(String query) throws SQLException {
        statement = getStatement();
        return statement.execute(query);
    }

    public ResultSet getResultSet() throws  SQLException {
        if (statement == null) {
            throw new SQLException("Previous statement unknown, have you executed executeRaw() before?");
        }
        return statement.getResultSet();
    }

}
