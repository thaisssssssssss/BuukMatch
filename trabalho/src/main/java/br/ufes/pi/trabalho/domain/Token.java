package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Token {
    @Id
    private String token;

    @ManyToOne
    private User user;

    public Token(String token, User user){
        setToken(token);
        setUser(user);
    }

    protected Token(){}
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
