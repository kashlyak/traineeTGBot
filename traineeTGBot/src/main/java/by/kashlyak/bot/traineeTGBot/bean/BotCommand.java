package by.kashlyak.bot.traineeTGBot.bean;

public enum BotCommand {
    NONE("",""),
    START("/start", "Стартуем"),
    HELLO("/hello", "Приветсвтие"),
    BYE("/bye", "Пока-пока"),
    INFO("Ввести личную информацию", "ФИО, Возраст, Адрес"),
    FIO("fio", "Введите ваши Имя И Фамилию"),
    ADDRESS("address", "Введите адрес"),
    AGE("age", "Введите Ваш возраст");
    final String command;
    final String description;

    BotCommand(String command, String description) {
        this.command = command;
        this.description=description;
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
            case "/bye":
                return BYE;
            case "Ввести личную информацию":
                return INFO;
            case "fio":
                return FIO;
            case "age":
                return AGE;
            case "address":
                return ADDRESS;
            default:
                return NONE;

        }
    }
}
