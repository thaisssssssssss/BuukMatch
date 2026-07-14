package br.ufes.pi.trabalho.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private String description;
    private LocalDateTime publicationDate;
    private byte [] photo;

    public PostResponse(String description, LocalDateTime publicationDate, byte [] photo){
        this.description = description;
        this.publicationDate = publicationDate;
        this.photo = photo;
    }

    protected PostResponse(){}
    
    public String getDescription() {
        return description;
    }

    public byte [] getPhoto() {
        return photo;
    }
    
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    

}
