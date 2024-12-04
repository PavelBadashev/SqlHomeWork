package homework.sqlObjects;

import homework.data.AnimalEnum;
import homework.sqlObjects.birds.Duck;
import homework.sqlTables.AnimalTable;
import homework.validator.ScannerValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnimalFactory {
    private final Scanner scanner;
    public AnimalTable animalTable;

    public AnimalFactory(Scanner scanner, AnimalTable table) {
        this.scanner = scanner;
        this.animalTable = table;
    }

    public Map<String, String> generateAnimalInfo(Scanner scanner) {
        ScannerValidator validator = new ScannerValidator(scanner);

        Map<String, String> animalInfo = new HashMap<>();

        // Ввод основных атрибутов животного
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
            Map<String, String> animalInfo = generateAnimalInfo(scanner);

            // Запись сгенерированных данных о животном
            String inputAnimalName = animalInfo.get("name");
            Integer inputAnimalAge = Integer.parseInt(animalInfo.get("age"));
            Integer inputAnimalWeight = Integer.parseInt(animalInfo.get("weight"));
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
}
