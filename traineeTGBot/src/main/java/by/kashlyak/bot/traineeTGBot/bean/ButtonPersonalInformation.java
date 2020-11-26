package by.kashlyak.bot.traineeTGBot.bean;

import by.kashlyak.bot.traineeTGBot.mainclass.TraineeBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ButtonPersonalInformation {
    @Autowired
    TraineeBot traineeBot;

    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("ФИО");
        inlineKeyboardButton1.setCallbackData("Введите ваше Имя и Фаили.");
        inlineKeyboardButton2.setText("Возраст");
        inlineKeyboardButton2.setCallbackData("Введите Ваш возраст");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Адрес").setCallbackData("Введите Ваш адрес"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);


        return new SendMessage().setChatId(chatId).setText("Пример").setReplyMarkup(inlineKeyboardMarkup);
    }


//    public  sendMessage()
//    Message message = update.getMessage();
////        if (message.hasText()) {
//            switch (message.getText()) {
//                case "Ввести личную информацию":
//                    try {
//                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
//
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//            }
//        } else if (update.hasCallbackQuery()) {
//            try {
//                execute(new SendMessage().setText(
//                        update.getCallbackQuery().getData())
//                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
    }

