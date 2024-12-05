package homework.data;

public enum BooleanEnum {
    Y,
    N;

    public static BooleanEnum fromString(String booleanEnum) {
        return BooleanEnum.valueOf(booleanEnum.trim().toUpperCase());
    }
}
