package br.ufes.pi.trabalho.domain;

import br.ufes.pi.trabalho.dto.BookRequest;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public class Book{
    private String title;
    private String author;
    private String publisher;
    private Integer numberOfPages;
    private Integer publicationYear;
    
    public Book(String title, String author, String publisher, Integer numberOfPages, Integer publicationYear){
        setTitle(title);
        setPublisher(publisher);
        setAuthor(author);
        setNumberOfPages(numberOfPages);
        setPublicationYear(publicationYear);
    }
    
    protected Book(){}

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

    public String getPublisher(){
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BookRequest createBookRequest(){
        return new BookRequest(title, author, publisher, numberOfPages, publicationYear);
    }
}