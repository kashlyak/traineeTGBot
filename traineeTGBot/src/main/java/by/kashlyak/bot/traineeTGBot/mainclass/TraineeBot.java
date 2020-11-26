package by.kashlyak.bot.traineeTGBot.mainclass;

import by.kashlyak.bot.traineeTGBot.bean.UpdateDispatcher;
import by.kashlyak.bot.traineeTGBot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
@PropertySource("application.properties")
@Component
public class TraineeBot  extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String BotUsername;
    @Value("${bot.token}")
    private String botToken;
    @Override
    public String getBotUsername() {
        return BotUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    @Autowired
    UpdateDispatcher updateDispatcher;
    @Override
    public void onUpdateReceived(Update update) {
        updateDispatcher.dispatch(update);


    }

}



