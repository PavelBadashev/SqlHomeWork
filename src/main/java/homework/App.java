package homework;

import homework.commands.Commands;
import homework.sqlTables.AnimalTable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        //Загрузум конфигурации для бд
        Properties properties = new Properties();
        InputStream input = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");
        properties.load(input);

        //Создание подключения к бд
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            //Создание запроса
            Scanner scanner = new Scanner(System.in); // Включил сканнер

            AnimalTable animalTable = new AnimalTable();

            List<String> columnsAnimalTable = new ArrayList<>();
            columnsAnimalTable.add("id INT AUTO_INCREMENT PRIMARY KEY");
            columnsAnimalTable.add("color VARCHAR(20)");
            columnsAnimalTable.add("name VARCHAR(20)");
            columnsAnimalTable.add("weight INT");
            columnsAnimalTable.add("type VARCHAR(20)");
            columnsAnimalTable.add("age INT");
            animalTable.create(columnsAnimalTable);

            while (true) {
                Commands command = new Commands(scanner, animalTable);
                command.executeCommand();
            }

        }
    }
}
