package by.kashlyak.bot.traineeTGBot.service;

import by.kashlyak.bot.traineeTGBot.utils.Processor.CommandProcessor;

public class MessageResponse {
    private Long chatId;
    private String response;



    public MessageResponse(long chatId, CommandProcessor processor) {
        this.chatId = chatId;
        this.response = processor.getResponse();
    }



    public Long getChatId() {
        return chatId;
    }

    public String getResponse() {
        return response;
    }
}
