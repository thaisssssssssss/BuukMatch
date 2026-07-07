package br.ufes.pi.trabalho.domain;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Post{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legend;
    private LocalDateTime publicationDate;
    private String photo;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @ManyToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private StatusPost status; 
    
    private String ownerName;

    private Book book;

    public Post(String legend, String photo, User owner, Book book){
        setLegend(legend);      
        setPhoto(photo);
        setOwner(owner);
        setPublicationDate(LocalDateTime.now());
        setStatus(StatusPost.DISPONIVEL);
        setOwnerName(owner.getName());
        setBook(book);
    }

    protected Post() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }
    
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public StatusPost getStatus() {
        return status;
    }

    public void setStatus(StatusPost status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addLike(Like like){
        this.likes.add(like);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}