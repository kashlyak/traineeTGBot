package by.kashlyak.bot.traineeTGBot.bean;

public class DefaultCommands extends CommandProcessor{
    @Override
    public String getResponse() {
        return printResponse();
    }
    public String printResponse() {
        BotCommand[] commands = BotCommand.values();
        StringBuffer description = new StringBuffer();

        for(BotCommand command : commands) {
          description.append(String.format("\n%s - %s", command.getCommand(), command.getDescription()));
        }
        return String.format("Hello," +
                "\n%s",description);
    }
}
