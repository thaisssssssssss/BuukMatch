package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public class Book{
    private String title;
    private String autor;
    private String cover;
    private String description;
    private Integer numberOfPages;
    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    private BookGenre genre;
    
    public Book(String title, String autor, String cover, String description, Integer numberOfPages, Integer publicationYear, BookGenre genre){
        setTitle(title);
        setAutor(autor);
        setCover(cover);
        setDescription(description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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