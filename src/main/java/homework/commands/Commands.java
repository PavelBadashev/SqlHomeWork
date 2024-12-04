package homework.commands;

import homework.data.CommandEnum;
import homework.sqlObjects.AnimalFactory;
import homework.sqlObjects.AnimalObject;
import homework.sqlTables.AnimalTable;

import java.sql.SQLException;
import java.util.*;

public class Commands {

    private final Scanner scanner;
    public AnimalTable animalTable;

    public Commands(Scanner scanner, AnimalTable animalTable) {
        this.scanner = scanner;
        this.animalTable = animalTable;
    }

    public void executeCommand() {

        System.out.println(Arrays.toString(CommandEnum.values())); // Вывод списка доступны видов животных
        System.out.print("Введите команду: ");
        String inputLine = scanner.nextLine();
        try {
            CommandEnum commandEnum = CommandEnum.fromString(inputLine);

            AnimalFactory animalFactory = new AnimalFactory(scanner, animalTable);

            switch (commandEnum) {
                case ADD:
                    AnimalObject newAnimal = animalFactory.createAnimal();
//                    animalTable.insert(new AbsAnimalObject(newAnimal));
                    break;

                case LIST:
                    animalTable.print(animalTable.selectAll());
                    break;

                case EDIT:
                    animalTable.print(animalTable.selectAll());

                    System.out.print("Введите идентификатор (id)  животного, которого хотите изменить: ");
                    Integer animalId = scanner.nextInt();
                    AnimalObject choosenAnimal = animalTable.read(animalId);

                    System.out.println("Введите обновленный набор данных");
                    Map<String, String> newAnimalInfo = animalFactory.generateAnimalInfo(scanner);

                    choosenAnimal.setAge(Integer.parseInt(newAnimalInfo.get("age")));
                    choosenAnimal.setWeight(Integer.parseInt(newAnimalInfo.get("weight")));
                    choosenAnimal.setColor(newAnimalInfo.get("color"));
                    choosenAnimal.setName(newAnimalInfo.get("name"));

                    animalTable.update(choosenAnimal);


                case EXIT:
                    System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nОшибка ввода комманды. Не обходимо ввести значение из списка.");
            this.executeCommand();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
