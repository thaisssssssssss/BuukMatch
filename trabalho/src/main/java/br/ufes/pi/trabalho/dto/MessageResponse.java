package br.ufes.pi.trabalho.dto;

import java.time.LocalDateTime;

public class MessageResponse {
    private LocalDateTime receiveDate;
    private String content;
    private String remetenteName;
    private boolean sentByMe;
    
    public MessageResponse(LocalDateTime receiveDate, String content, String remetente, boolean sentByMe){
        setReceiveDate(receiveDate);
        setContent(content);
        setRemetenteName(remetente);
        setSentByMe(sentByMe);
    }
    
    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }
    public String getRemetenteName() {
        return remetenteName;
    }

    public void setRemetenteName(String remetenteName) {
        this.remetenteName = remetenteName;
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
