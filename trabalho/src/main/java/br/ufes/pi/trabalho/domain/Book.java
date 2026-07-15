package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public class Book{
    private String title;
    private String author;
    private String cover;
    private Integer numberOfPages;
    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    private BookGenre genre;
    
    public Book(String title, String author, Integer numberOfPages, Integer publicationYear, BookGenre genre){
        setTitle(title);
        setAuthor(author);
        setNumberOfPages(numberOfPages);
        setPublicationYear(publicationYear);
        setGenre(genre);
    }
    
    protected Book(){}

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    
    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
}