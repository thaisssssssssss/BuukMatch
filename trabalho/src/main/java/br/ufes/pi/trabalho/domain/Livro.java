package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Livro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String capa;
    @Enumerated(EnumType.STRING)
    private GeneroLivro genero;
    private Integer anoPublicacao;
    
    Livro(String titulo, String autor, String capa, GeneroLivro genero, Integer anoPublicacao){
        this.titulo = titulo;
        this.autor = autor;
        this.capa = capa;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
    }
    Livro(){}

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    public String getAutor() {
        return autor;
    }
    public String getCapa() {
        return capa;
    }
    public GeneroLivro getGenero() {
        return genero;
    }
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
}