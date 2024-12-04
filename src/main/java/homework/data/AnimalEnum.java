package homework.data;

public enum AnimalEnum {
    DOG,
    CAT,
    DUCK;

    public static AnimalEnum fromString(String animalEnum){
        return AnimalEnum.valueOf(animalEnum.trim().toUpperCase());
    }
}