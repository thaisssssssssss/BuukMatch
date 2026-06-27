package br.ufes.pi.trabalho.domain;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Match{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ArrayList<Message> mensagens;
    
    @ManyToOne
    private User User1;
    
    @ManyToOne
    private User User2;

    public Match (User User1, User User2){
        this.User1 = User1;
        this.User2 = User2;
        this.mensagens = new ArrayList<>();

    }

    public void adicionarMessage(Message Message) {
        mensagens.add(Message);
    }

    public List<Message> getMensagens() {
        return mensagens;
    }

    public User getUser1() {
        return User1;
    }
    public User getUser2() {
        return User2;
    }
    
}