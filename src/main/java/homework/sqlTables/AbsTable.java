package homework.sqlTables;

import homework.db.IDataBaseConnect;
import homework.db.MySqlConnector;

import java.sql.ResultSet;
import java.util.List;

public abstract class AbsTable implements ITable {
    protected IDataBaseConnect dbConnector = null;
    private String tableName = "";

    public AbsTable(String tableName) {
        dbConnector = new MySqlConnector();
        this.tableName = tableName;
    }

    @Override
    public void delete() {
        dbConnector.execute(String.format("DROP TABLE IF EXISTS %s", this.tableName));
    }

    @Override
    public void create(List<String> columns) {
        delete();
        dbConnector.execute(
                String.format(
                        "CREATE TABLE %s (%s);", tableName, String.join(",", columns)
                )
        );
    }

    public ResultSet selectAll() {
        return dbConnector.executeQuery(String.format("SELECT * FROM %s;", this.tableName));
    }

    public ResultSet getById(Integer id) {
        return dbConnector.executeQuery(String.format("SELECT * FROM %s WHERE id = %s;", this.tableName, id));
    }

    public ResultSet getByType(String type) {
        return dbConnector.executeQuery(String.format("SELECT * FROM %s WHERE type = '%s';", this.tableName, type));
    }

}
