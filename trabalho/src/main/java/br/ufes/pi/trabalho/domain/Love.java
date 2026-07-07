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
    
    private String userName;

    public Love(User user, Post Post){
        this.user =  user;
        this.post = Post;
        setUserName(user.getName());
    }

    protected Love(){}

    public Post getPost() {
        return post;
    }
    
    public User getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}