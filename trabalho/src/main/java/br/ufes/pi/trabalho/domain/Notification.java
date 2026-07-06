package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    
    private boolean read = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Match match;
    
    public Notification(User user, Match match, String message){
        setUser(user);
        setMatch(match);
        setMessage(message);
    }
    
    protected Notification(){}

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
