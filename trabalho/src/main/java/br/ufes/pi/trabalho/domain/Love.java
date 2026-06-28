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
    User user;

    @ManyToOne
    Post  post;
    
    public Love(User User, Post Post){
        this.user =  User;
        this.post = Post;
    }

    protected Love(){}

    public Post getPost() {
        return post;
    }
    
    public User getUser() {
        return user;
    }
}