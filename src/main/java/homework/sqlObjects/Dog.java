package homework.sqlObjects;

public class Dog extends AnimalObject {
    public Dog(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    public Dog(int id, String color, String name, int weight, String type, int age) {
        super(id, color, name, weight, type, age);
    }

    @Override
    public void say() {
        System.out.println("ГАВ");
    }

    @Override
    public void go() {
        System.out.println("Собака идет");
    }

    @Override
    public void eat() {
        System.out.println("Собака ест");
    }

    @Override
    public void drink() {
        System.out.println("Собака пьет");
    }
}
