package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    User user;

    @ManyToOne
    Post  post;
    
    public Like(User User, Post Post){
        this.user =  User;
        this.post = Post;
    }

    protected Like(){}

    public Post getPost() {
        return post;
    }
    
    public User getUser() {
        return user;
    }

}
