package br.ufes.pi.trabalho.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String description;
    private LocalDateTime publicationDate;
    private byte [] photo;
    private String ownerName;

    
    public PostResponse(Long id, String description, LocalDateTime publicationDate, byte [] photo, String ownerName){
        setId(id);
        setDescription(description);
        setPublicationDate(publicationDate);
        setPhoto(photo);
        setOwnerName(ownerName);
    }
    
    protected PostResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte [] getPhoto() {
        return photo;
    }

    public void setPhoto(byte [] photo) {
        this.photo = photo;
    }
    
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

}
