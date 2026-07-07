package br.ufes.pi.trabalho.dto;

public class NotificationResponse {
    private String message;
    
    public NotificationResponse(String message){
        setMessage(message);
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
}
