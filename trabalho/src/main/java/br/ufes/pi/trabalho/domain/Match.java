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

    private ArrayList<Mensagem> mensagens;
    
    @ManyToOne
    private Usuario usuario1;
    
    @ManyToOne
    private Usuario usuario2;

    public Match (Usuario usuario1, Usuario usuario2){
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.mensagens = new ArrayList<>();

    }

    public void adicionarMensagem(Mensagem mensagem) {
        mensagens.add(mensagem);
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }
    public Usuario getUsuario2() {
        return usuario2;
    }
    
}