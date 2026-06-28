package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Love{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    User User;

    @ManyToOne
    Post  Post;
    
    public Love(User User, Post Post){
        this.User =  User;
        this.Post = Post;
    }

    protected Love(){}

    public Post getPost() {
        return Post;
    }
    
    public User getUser() {
        return User;
    }
}