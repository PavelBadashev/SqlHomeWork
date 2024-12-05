package homework.sqlObjects;

public abstract class AnimalObject implements iObjectDB {
    private int id;
    private String type, name, color;
    private int weight, age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void say();

    public abstract void go();

    public abstract void eat();

    public abstract void drink();

    private String ageStringConvert(){
        if (age % 10 == 1 && age % 100 != 11) {
            return age + " год";
        }
        if (age % 10 >= 2 && age % 10 <= 4 && (age % 100 < 10 || age % 100 >= 20)) {
            return age + " года";
        }
        return age + " лет";
    }
    @Override
    public String toString() {
        return "        Привет! Меня зовут "
                + name + ". Мне "
                + ageStringConvert()
                + ". Я вешу - "
                + weight
                + " кг. Мой цвет - "
                + color;
    }

    // Нужен для создания нового объекта в БД
    public AnimalObject(String color, String name, int weight, String type, int age) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.age = age;
    }

    // Нужен для получения объекта из БД
    public AnimalObject(int id, String color, String name, int weight, String type, int age) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.age = age;
    }
}
