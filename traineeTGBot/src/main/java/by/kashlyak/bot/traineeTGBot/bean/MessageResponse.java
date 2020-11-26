package by.kashlyak.bot.traineeTGBot.bean;

public class MessageResponse {
    private Long chatId;
    private String response;

    public MessageResponse() {
    }

    public MessageResponse(long chatId, CommandProcessor processor) {
        this.chatId = chatId;
        this.response = processor.getResponse();
    }
    public MessageResponse (long chatId, String response) {
        this.chatId = chatId;
        this.response =response;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getResponse() {
        return response;
    }
}
