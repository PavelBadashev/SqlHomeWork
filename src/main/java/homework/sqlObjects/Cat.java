package homework.sqlObjects;

public class Cat extends AnimalObject {
    String type = this.getClass().getSimpleName()
            ;
    public Cat(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    public Cat(int id, String color, String name, int weight, String type, int age) {
        super(id, color, name, weight, type, age);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }

    @Override
    public void go() {
        System.out.println("Бежит");
    }

    @Override
    public void eat() {
        System.out.println("Кушает");
    }

    @Override
    public void drink() {
        System.out.println("Пьет воду");
    }
}
