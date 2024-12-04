package homework.sqlObjects.birds;

import homework.sqlObjects.AnimalObject;

public abstract class Bird extends AnimalObject implements Flying {
    public Bird(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    public Bird(int id, String color, String name, int weight, String type, int age) {
        super(id, color, name, weight, type, age);
    }

    @Override
    public void fly() {
        System.out.println("Летит!");
    }
}
