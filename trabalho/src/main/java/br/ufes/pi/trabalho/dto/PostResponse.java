package br.ufes.pi.trabalho.dto;

import java.time.LocalDateTime;

import br.ufes.pi.trabalho.dto.BookRequest;

public class PostResponse {
    private Long id;
    private String legend;
    private LocalDateTime publicationDate;
    private byte [] photo;
    private String ownerName;
    private BookRequest book;

    
    public PostResponse(Long id, String legend, LocalDateTime publicationDate, byte [] photo, String ownerName, BookRequest book){
        setId(id);
        setLegend(legend);
        setBook(book);
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
    
    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
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
    public BookRequest getBook() {
        return book;
    }
    public void setBook(BookRequest book) {
        this.book = book;
    }
}
