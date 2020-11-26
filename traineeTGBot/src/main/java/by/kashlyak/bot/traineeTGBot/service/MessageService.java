package by.kashlyak.bot.traineeTGBot.service;

import by.kashlyak.bot.traineeTGBot.bean.ButtonPersonalInformation;
import by.kashlyak.bot.traineeTGBot.bean.MessageResponse;
import by.kashlyak.bot.traineeTGBot.mainclass.TraineeBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;
@Service

public class MessageService {
    @Autowired
    TraineeBot traineeBot;


    public static void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Ввести личную информацию"));


        keyboardRowList.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);


    }

    public void sendMessage(MessageResponse response){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(response.getChatId());
        sendMessage.setText(response.getResponse());
        sendMessage.enableMarkdown(true);
        try {

            MessageService.setButton(sendMessage);
            traineeBot.execute(sendMessage);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message, String response) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);

        if(message.hasText()) {
            if(message.getText().equals("Ввести личную информацию")) {
                try {
                    traineeBot.execute(ButtonPersonalInformation.sendInlineKeyBoardMessage(message.getChatId()));
                } catch (TelegramApiException e)  {
                    e.printStackTrace();
                }
            }
            try {

            MessageService.setButton(sendMessage);
            traineeBot.execute(sendMessage);
        } catch (TelegramApiException e ) {
            e.printStackTrace();
        }
    }
    }


}
