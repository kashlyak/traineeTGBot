package by.kashlyak.bot.traineeTGBot.bean;

import by.kashlyak.bot.traineeTGBot.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class UpdateDispatcher {
    @Autowired
    MessageService messageService;

    public void dispatch(Update update) {





        BotCommand command = BotCommand.chooseCommand(update.getMessage().getText());
        switch (command) {
            case INFO:
                messageService.sendMessage(update.getMessage(), "Allilyia");
                break;
            case START:
                messageService.sendMessage(update.getMessage(), "new Hello()");
                break;
            case HELLO:

            case BYE:

            default:
                messageService.sendMessage(new MessageResponse(update.getMessage().getChatId(), new NonSupported()));
        }


    }
}
