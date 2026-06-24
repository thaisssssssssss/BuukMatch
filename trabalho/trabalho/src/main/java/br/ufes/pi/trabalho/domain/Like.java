package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Like{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    Usuario usuario;

    @ManyToOne
    Postagem  postagem;
    
    public Like(Usuario usuario, Postagem postagem){
        this.usuario =  usuario;
        this.postagem = postagem;
    }
    public Postagem getPostagem() {
        return postagem;
    }
    public Usuario getUsuario() {
        return usuario;
    }
}