package br.ufes.pi.trabalho.dto;

public class ChatResponse {
    private String name;
    private String lastMessage;

    public ChatResponse(){}

    public String getLastMessage() {
        return lastMessage;
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
