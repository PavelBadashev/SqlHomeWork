package homework.sqlTables;

import homework.sqlObjects.AnimalObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void update(AnimalObject animalObject) {
        this.dbConnector.execute(
                // Изменять тип животного не логично, поэтому такой возможности нет в методе update
                String.format(
                        "UPDATE %s SET COLOR = '%s', NAME = '%s', WEIGHT = '%s', AGE = '%s' WHERE ID = '%s';",
                        NAME,
                        animalObject.getColor(),
                        animalObject.getName(),
                        animalObject.getWeight(),
                        animalObject.getAge(),
                        animalObject.getId()
                )
        );
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
    }

    public ArrayList<AnimalObject> read() throws SQLException {
        ArrayList<AnimalObject> animalObjectList = new ArrayList<>();
        ResultSet resultSet;

        resultSet = selectAll();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String color = resultSet.getString("COLOR");
            String name = resultSet.getString("NAME");
            int weight = resultSet.getInt("WEIGHT");
            String type = resultSet.getString("TYPE");
            int age = resultSet.getInt("AGE");

            AnimalObject animal = new AnimalObject(id, color, name, weight, type, age);
            animalObjectList.add(animal);
        }

        return animalObjectList;
    }

    public AnimalObject read(Integer id) throws SQLException {
        try {
            ResultSet resultSet = getById(id);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                String color = resultSet.getString("COLOR");
                String name = resultSet.getString("NAME");
                int weight = resultSet.getInt("WEIGHT");
                String type = resultSet.getString("TYPE");
                int age = resultSet.getInt("AGE");

                return new AnimalObject(id, color, name, weight, type, age);

            }
        }
        catch (Exception ex) {
            throw ex;
        }
        return null;
    }
}
