package by.kashlyak.bot.traineeTGBot.utils.enums;

public enum BotCommand {
    NONE("", ""),
    START("/start", "Стартуем"),
    HELLO("/hello", "Приветсвтие"),
    INFO("Показать мои данные", "Пока-пока"),
    INSERT_INFO("Ввести личную информацию", "ФИО, Возраст, Адрес"),
    FIO("FIO", "личную"),
    ADDRESS("ADDRESS", "ADDRESS"),
    AGE("AGE", "AGE"),
    PHOTO("PHOTO", "PHOTO");
    final String command;
    final String description;

    BotCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public static BotCommand chooseCommand(String command) {
        switch (command) {
            case "/start":
                return START;
            case "/hello":
                return HELLO;
            case "Показать мои данные":
                return INFO;
            case "Ввести личную информацию":
                return INSERT_INFO;
            case "FIO":
                return FIO;
            case "AGE":
                return AGE;
            case "ADDRESS":
                return ADDRESS;
            case "Загрузить фотографию":
                return PHOTO;
            default:
                return NONE;

        }
    }
}
