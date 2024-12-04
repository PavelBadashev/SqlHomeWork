package homework.data;

public enum CommandEnum {
    ADD,
    LIST,
    EDIT,
    EXIT;

    public static CommandEnum fromString(String command){
        return CommandEnum.valueOf(command.trim().toUpperCase());
    }
}