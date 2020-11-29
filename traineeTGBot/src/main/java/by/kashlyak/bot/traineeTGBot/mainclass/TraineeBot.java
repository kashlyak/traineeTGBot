package by.kashlyak.bot.traineeTGBot.mainclass;

import by.kashlyak.bot.traineeTGBot.service.UpdateDispatcher;
import by.kashlyak.bot.traineeTGBot.service.MessageService;
import by.kashlyak.bot.traineeTGBot.service.WorkWithDBFromMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@PropertySource("application.properties")
@Component
public class TraineeBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Autowired
    UpdateDispatcher updateDispatcher;
    @Autowired
    MessageService messageService;
    @Autowired
    WorkWithDBFromMessage workWithDB;


    public void setStatusMessage(int statusMessage) {
        this.statusMessage = statusMessage;
    }

    int statusMessage = 0;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            workWithDB.editChatIdForPersonFromMessage(update);
        }
        if (update.hasCallbackQuery()) {
            statusMessage = messageService.forStatusMessage(update);
            messageService.sendMessageForButtons(update);

        } else if (statusMessage == 0) {

            updateDispatcher.dispatchWithoutCallbackQuery(update);

        } else if (statusMessage == 1) {

            workWithDB.editFullNamePersonFromMessage(update);
            statusMessage = 0;

        } else if (statusMessage == 2) {

            workWithDB.editAgeForPersonFromMessage(update);

            statusMessage = 0;
        } else if (statusMessage == 3) {
            workWithDB.editAddressForPersonFromMessage(update);
            statusMessage = 0;

        } else if (statusMessage == 4) {
            messageService.responseOnMessageWithPhoto(update);
            statusMessage = 0;
        }
    }


}



