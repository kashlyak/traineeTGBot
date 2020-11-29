package by.kashlyak.bot.traineeTGBot.service;

import by.kashlyak.bot.traineeTGBot.Processor.NonSupported;
import by.kashlyak.bot.traineeTGBot.bean.user.Person;
import by.kashlyak.bot.traineeTGBot.enums.BotCommand;
import by.kashlyak.bot.traineeTGBot.mainclass.TraineeBot;
import by.kashlyak.bot.traineeTGBot.service.MessageResponse;
import by.kashlyak.bot.traineeTGBot.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class UpdateDispatcher {
    @Autowired
    MessageService messageService;
    @Autowired
    TraineeBot traineeBot;
    @Autowired
    Person person;

    public void dispatchWithoutCallbackQuery(Update update) {

        BotCommand command = BotCommand.chooseCommand(update.getMessage().getText());
        switch (command) {
            case INSERT_INFO:
                messageService.sendMessage(update.getMessage(), " ");
                break;
            case START:

                messageService.sendMessage(update.getMessage(), "Приветсвую");
                break;
            case HELLO:
                messageService.sendMessage(update.getMessage(), person.toString());
                break;
            case INFO:
                messageService.sendPersonalInfoWithMessage(update.getMessage());
                break;
            case PHOTO:
                messageService.sendMessage(update.getMessage(), "Загрузите фотографию");
                traineeBot.setStatusMessage(4);
                break;
            default:
                messageService.sendMessage(new MessageResponse(update.getMessage().getChatId(), new NonSupported()));
        }
    }






}
