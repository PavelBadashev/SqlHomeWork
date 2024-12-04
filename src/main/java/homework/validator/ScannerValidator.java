package homework.validator;

import java.util.Scanner;

public class ScannerValidator {
    private final Scanner scanner;

    public ScannerValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void validateString(String scannerLine){
        if (scannerLine == null || scannerLine.isEmpty() || scannerLine.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
    }

    public void validateNumber(Integer scannerNumber){
        if (scannerNumber <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String inputString(){
        try {
//            System.out.println(scanner.nextLine());
            String name = scanner.nextLine();
            validateString(name);
            return name;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Ошибка ввода информации о животном. Необходимо ввести строку.");
            return inputString();
        }
    }

    public Integer inputNumber(){
        try {
            Integer number = Integer.valueOf(scanner.nextLine());
            validateNumber(number);
            return number;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода информации о животном. Необходимо ввести число.");
            return inputNumber();
        }
    }
}
