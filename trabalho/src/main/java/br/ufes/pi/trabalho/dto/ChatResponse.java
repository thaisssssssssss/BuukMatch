package br.ufes.pi.trabalho.dto;

public class ChatResponse {
    private String name;
    private String lastMessage;
    private Long chatId;
    
    public ChatResponse(Long chatId){
        setChatId(chatId);
    }
    
    public String getLastMessage() {
        return lastMessage;
    }
    
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
    
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
