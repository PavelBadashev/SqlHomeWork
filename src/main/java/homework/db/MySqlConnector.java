package homework.db;

import homework.sqlSettings.Settings;

import java.sql.*;
import java.util.Map;

public class MySqlConnector implements IDataBaseConnect {
    private static Connection connection = null;
    private static Statement statement = null;

    private void open() {
        Settings configReader = new Settings();
        Map<String, String> configData = configReader.getSettings();

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        configData.get("url"),
                        configData.get("username"),
                        configData.get("password")
                );
            }

            if (statement == null) {
                statement = connection.createStatement();
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close() {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void execute(String sqlRequest) {
        this.open();
        try {
            statement.execute(sqlRequest);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeQuery(String sqlRequest) {
        this.open();
        try {
            return statement.executeQuery(sqlRequest);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
