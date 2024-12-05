package homework.sqlTables;

import homework.sqlObjects.AnimalObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTable extends AbsTable {
    private static final String NAME = "animals";

    public AnimalTable() {
        super(NAME);
    }

    // Создаем новые записи (животных)
    public void insert(AnimalObject animalObject) {
        this.dbConnector.execute(
                String.format(
                        "INSERT INTO %s (id, color, name, weight, type, age) " + "VALUES('%s', '%s', '%s', '%s', '%s', '%s')",
                        NAME,
                        animalObject.getId(),
                        animalObject.getColor(),
                        animalObject.getName(),
                        animalObject.getWeight(),
                        animalObject.getType(),
                        animalObject.getAge()
                )
        );
    }

    // Создаем новые записи (животных)
    public void update(Integer id, String name, Integer age, Integer weight, String color) {
        this.dbConnector.execute(String.format(
                "UPDATE %s SET COLOR = '%s', NAME = '%s', WEIGHT = '%s', AGE = '%s' WHERE ID = '%s';",
                NAME,
                color,
                name,
                weight,
                age,
                id
        ));
    }

    public void print(ResultSet rs) throws SQLException {
        //Вывод заголовка на экран
        System.out.printf("%-5s %-10s %-20s %-5s %-10s %-5s%n", "ID", "Color", "Name", "Weight", "Type", "Age");
        System.out.println("____________________________________________________________");

        //Вывод данных по запросу
        while (rs.next()) {
            System.out.printf("%-5s %-10s %-20s %-5s  %-10s %-5s%n",
                    rs.getInt("id"),
                    rs.getString("color"),
                    rs.getString("name"),
                    rs.getString("weight"),
                    rs.getString("type"),
                    rs.getInt("age"));
        }
        System.out.println('\n');
    }

    public void read() throws SQLException {
        ResultSet resultSet;

        resultSet = selectAll();
        print(resultSet);
    }

    public ResultSet read(Integer id) throws SQLException {
        try {
            ResultSet resultSet = getById(id);
            return resultSet;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
