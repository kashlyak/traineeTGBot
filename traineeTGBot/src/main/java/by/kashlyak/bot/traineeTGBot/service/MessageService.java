package by.kashlyak.bot.traineeTGBot.service;

import by.kashlyak.bot.traineeTGBot.enums.BotCommand;
import by.kashlyak.bot.traineeTGBot.bean.buttons.Buttons;
import by.kashlyak.bot.traineeTGBot.bean.user.Person;
import by.kashlyak.bot.traineeTGBot.dbworker.DBWorker;
import by.kashlyak.bot.traineeTGBot.mainclass.TraineeBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.Comparator;
import java.util.List;

@Service

public class MessageService {
    @Autowired
    TraineeBot traineeBot;
    @Autowired
    Person person;


    public void sendMessage(MessageResponse response) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(response.getChatId());
        sendMessage.setText(response.getResponse());
        sendMessage.enableMarkdown(true);

        try {
            Buttons.setButton(sendMessage);
            traineeBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPersonalInfoWithMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(DBWorker.personFromDB(message.getChatId()));

        try {
            Buttons.setButton(sendMessage);
            traineeBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message, String response) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);

        if (message.hasText()) {
            if (message.getText().equals("Ввести личную информацию")) {

                try {
                    traineeBot.execute(Buttons.sendInlineKeyBoardMessage(message.getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            try {
                Buttons.setButton(sendMessage);
                traineeBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();

            }
        }
    }

    public void sendPhoto(Message message, String photoId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setPhoto(photoId);
        sendPhoto.setCaption("Вот что вы прислали.");
        try {
            Buttons.setButton(new SendMessage());
            traineeBot.execute(sendPhoto);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendMessageForButtons(Update update) {
        try {
            Buttons.setButton(new SendMessage().setText(
                    update.getCallbackQuery().getData())
                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            traineeBot.execute(new SendMessage().setText(
                    update.getCallbackQuery().getData())
                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public int forStatusMessage(Update update) {
        int forStatusMessage;
        String data = update.getCallbackQuery().getData();
        BotCommand command = BotCommand.chooseCommand(data);

        switch (command) {
            case FIO:
                forStatusMessage = 1;

                return forStatusMessage;
            case AGE:
                forStatusMessage = 2;
                return forStatusMessage;
            case ADDRESS:
                forStatusMessage = 3;
                return forStatusMessage;
            default:
                forStatusMessage = 0;
                return forStatusMessage;
        }
    }
    public void responseOnMessageWithPhoto(Update update) {
        List<PhotoSize> photos = update.getMessage().getPhoto();
        String f_id = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getFileId();

        sendPhoto(update.getMessage(), f_id);

    }


}
