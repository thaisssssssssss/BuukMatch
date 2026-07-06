package br.ufes.pi.trabalho.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Match{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user1;
    
    @ManyToOne
    private User user2;

    @OneToOne(mappedBy = "match", cascade = CascadeType.ALL)
    private Chat chat;

    private String user1Name;
    private String user2Name;

    public Match (User user1, User user2){
        setUser1(user1);
        setUser2(user2);
        setUser1Name(user1.getName());
        setUser2Name(user2.getName());
    }
       
    protected Match(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }
    
    public User getUser1() {
        return user1;
    }
    
    public void setUser2(User user2) {
        this.user2 = user2;
    }
    
    public User getUser2() {
        return user2;
    }
       
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }
}