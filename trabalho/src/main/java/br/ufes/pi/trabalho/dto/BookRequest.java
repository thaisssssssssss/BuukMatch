package br.ufes.pi.trabalho.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class BookRequest{
    private String title;
    private String author;
    private String publisher;
    private Integer numberOfPages;
    private Integer publicationYear;

    
    public BookRequest(String title, String author, String publisher, Integer numberOfPages, Integer publicationYear){
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setNumberOfPages(numberOfPages);
        setPublicationYear(publicationYear);
    }
    
    protected BookRequest(){}

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}