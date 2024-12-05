package homework.commands;

import homework.data.CommandEnum;
import homework.sqlObjects.AnimalFactory;
import homework.sqlTables.AnimalTable;

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
                    animalFactory.createAnimal();
                    break;

                case LIST:
                    animalFactory.getListOfAnimal();
                    break;

                case EDIT:
                    animalFactory.editAnimal();
                    break;

                case EXIT:
                    System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nОшибка ввода комманды. Не обходимо ввести значение из списка.");
            this.executeCommand();
        }
    }
}
