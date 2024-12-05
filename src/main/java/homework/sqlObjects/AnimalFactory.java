package homework.sqlObjects;

import homework.data.AnimalEnum;
import homework.data.BooleanEnum;
import homework.sqlObjects.birds.Duck;
import homework.sqlTables.AnimalTable;
import homework.validator.ScannerValidator;

import java.sql.SQLException;
import java.util.*;

public class AnimalFactory {
    private final Scanner scanner;
    public AnimalTable animalTable;

    public AnimalFactory(Scanner scanner, AnimalTable table) {
        this.scanner = scanner;
        this.animalTable = table;
    }

    public Map<String, String> generateAnimalInfo() {
        ScannerValidator validator = new ScannerValidator(scanner);

        Map<String, String> animalInfo = new HashMap<>();

        System.out.print("Введите кличку животного: ");
        animalInfo.put("name", validator.inputString());

        System.out.print("Введите возраст животного: ");
        animalInfo.put("age", validator.inputNumber().toString());

        System.out.print("Введите вес животного: ");
        animalInfo.put("weight", validator.inputNumber().toString());

        System.out.print("Введите цвет животного: ");
        animalInfo.put("color", validator.inputString());

        return animalInfo;
    }

    public AnimalObject createAnimal() {
        // Ввод тип животного
        System.out.println(Arrays.toString(AnimalEnum.values())); // Вывод списка доступны видов животных
        System.out.println("Какое животное вы хотите добавить? (Выберите из приведенного выше списка)");
        String inputLineAnimal = scanner.nextLine(); // Ввод значения пользователем

        try {
            // Генерация данных о животном (опрос)
            AnimalEnum animalTypeCommand = AnimalEnum.fromString(inputLineAnimal);
            Map<String, String> animalInfo = generateAnimalInfo();

            // Запись сгенерированных данных о животном
            String inputAnimalName = animalInfo.get("name");
            int inputAnimalAge = Integer.parseInt(animalInfo.get("age"));
            int inputAnimalWeight = Integer.parseInt(animalInfo.get("weight"));
            String inputAnimalColor = animalInfo.get("color");

            switch (animalTypeCommand) {
                case CAT:
                    Cat cat = new Cat(
                            inputAnimalColor,
                            inputAnimalName,
                            inputAnimalWeight,
                            animalTypeCommand.toString(),
                            inputAnimalAge
                    );
                    animalTable.insert(cat);
                    cat.say();
                    return cat;

                case DUCK:
                    Duck duck = new Duck(
                            inputAnimalColor,
                            inputAnimalName,
                            inputAnimalWeight,
                            animalTypeCommand.toString(),
                            inputAnimalAge
                    );
                    animalTable.insert(duck);
                    duck.say();
                    duck.fly();
                    return duck;

                case DOG:
                    Dog dog = new Dog(
                            inputAnimalColor,
                            inputAnimalName,
                            inputAnimalWeight,
                            animalTypeCommand.toString(),
                            inputAnimalAge
                    );
                    animalTable.insert(dog);
                    dog.say();
                    return dog;

                default:
                    System.out.println("Ошибка. Выберите из ранее приведенного списка!");
                    return null;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода информации о животном. Необходимо ввести значение из списка.");
            return createAnimal();
        }
    }

    public void getListOfAnimal() {
        try {
            ScannerValidator validator = new ScannerValidator(scanner);

            System.out.println("Требуется ли фильтровать животных по фильтру? (Y/N)");

            String filterType = validator.inputString();
            BooleanEnum booleanEnum = BooleanEnum.fromString(filterType);
            switch (booleanEnum) {
                case Y:
                    System.out.print("Введите тип по которому будете фильтровать: ");
                    AnimalEnum animalTypeCommand = AnimalEnum.fromString(validator.inputString());
                    animalTable.print(animalTable.getByType(animalTypeCommand.toString()));
                    break;
                case N:
                    animalTable.read();
                    break;
            }
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Ошибка ввода информации о животном. Необходимо ввести значение из списка.");
            getListOfAnimal();
        }
    }

    public void editAnimal() {
        try {
            ScannerValidator validator = new ScannerValidator(scanner);

            System.out.print("Введите идентификатор (id)  животного, которого хотите изменить: ");
            Integer animalId = validator.inputNumber();

            if (!animalTable.getById(animalId).next()) {
                System.out.println("Введен не существующий идентификатор животного");
                editAnimal();
            }

            System.out.println("Введите обновленный набор данных:");
            Map<String, String> newAnimalInfo = generateAnimalInfo();

            animalTable.update(
                    animalId,
                    newAnimalInfo.get("name"),
                    Integer.parseInt(newAnimalInfo.get("age")),
                    Integer.parseInt(newAnimalInfo.get("weight")),
                    newAnimalInfo.get("color")
            );
        }
        catch (IllegalArgumentException e) {
            System.out.println("Ошибка при вводе идентификатора животного");
            editAnimal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
