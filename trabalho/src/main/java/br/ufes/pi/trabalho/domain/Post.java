package br.ufes.pi.trabalho.domain;

import br.ufes.pi.trabalho.domain.Like;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Post{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime publicationDate;
    private String photo;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;
    // private int nLikes;

    @ManyToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private StatusPost status; 
    
    // tem que inicializar o dono
    public Post(String description, String photo, User owner){
        setDescription(description);
        setPhoto(photo);
        setOwner(owner);
        setPublicationDate(LocalDateTime.now());
        setStatus(StatusPost.DISPONIVEL);
        setLikes(new ArrayList<Like>());
    }

    protected Post() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public int getNLikes(){
        return likes.size();
    }
    public void addLike(Like like){
        this.likes.add(like);
    }
    public List<Like> getLikes() {
        return likes;
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
    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }
}