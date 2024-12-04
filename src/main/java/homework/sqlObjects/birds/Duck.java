package homework.sqlObjects.birds;

public class Duck extends Bird {
    public Duck(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    public Duck(int id, String color, String name, int weight, String type, int age) {
        super(id, color, name, weight, type, age);
    }

    @Override
    public void say() {
        System.out.println("Кря!");
    }

    @Override
    public void go() {
        System.out.println("Утка идет!");
    }

    @Override
    public void eat() {
        System.out.println("Утка ест!");
    }

    @Override
    public void drink() {
        System.out.println("Утка пьет!");
    }

    @Override
    public void fly() {
        System.out.println("Утка летит!");
    }
}
