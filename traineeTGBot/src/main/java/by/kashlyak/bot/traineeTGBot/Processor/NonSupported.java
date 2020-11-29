package by.kashlyak.bot.traineeTGBot.Processor;

public class NonSupported extends CommandProcessor {
    @Override
    public String getResponse() {
        return "Я такое не умею";
    }
}
