package homework.db;

import java.sql.ResultSet;

public interface IDataBaseConnect {
    void execute(String sqlRequest);

    ResultSet executeQuery(String sqlResult);
}
