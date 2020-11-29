package by.kashlyak.bot.traineeTGBot.service;

import by.kashlyak.bot.traineeTGBot.bean.user.Person;
import by.kashlyak.bot.traineeTGBot.dbworker.DBWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class WorkWithDBFromMessage {
    @Autowired
    Person person;

    public void editFullNamePersonFromMessage(Update update) {
        person.setFullName(update.getMessage().getText());
        DBWorker.writeFullNameOnDB(person.getFullName(), person.getChatId());
    }

    public void editAgeForPersonFromMessage(Update update) {
        person.setAge(Integer.parseInt(update.getMessage().getText()));
        DBWorker.writeAgeOnDB(person.getAge(), update.getMessage().getChatId());
    }

    public void editAddressForPersonFromMessage(Update update) {
        person.setAddress(update.getMessage().getText());
        DBWorker.writeAddressOnDB(person.getAddress(), update.getMessage().getChatId());
    }

    public void editChatIdForPersonFromMessage(Update update) {
        person.setChatId(update.getMessage().getChatId());
        DBWorker.writeChatIdOnDB(person.getChatId());
    }
}
