package br.ufes.pi.trabalho.dto;

import java.time.LocalDateTime;

public class MessageResponse {
    private LocalDateTime receiveDate;
    private String content;

    public MessageResponse(LocalDateTime receiveDate, String content){
        setReceiveDate(receiveDate);
        setContent(content);
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }
}
