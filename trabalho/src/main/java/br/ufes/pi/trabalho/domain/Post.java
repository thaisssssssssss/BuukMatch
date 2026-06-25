package br.ufes.pi.trabalho.domain;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Post{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime publicationDate;
    private String photo;

    @ManyToOne
    private User dono;
    
    private StatusPost status;
    
    @Enumerated(EnumType.STRING)
    private StatusPost _status; 
    // tem que inicializar o dono
    public Post(String description, String photo){
        this.description = description;
        this.photo = photo;
        this.publicationDate = LocalDateTime.now();
        this.status = StatusPost.RESERVADO;
    }
    public Post() {}

    public String getDescription() {
        return description;
    }
    public String getPhoto() {
        return photo;
    }
    public StatusPost getStatus() {
        return status;
    }
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    public Long getId() {
        return id;
    }

    public User getDono() {
        return dono;
    }
}