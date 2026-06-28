package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String capa;
    @Enumerated(EnumType.STRING)
    private BookGenre Genre;
    private Integer anoPublicacao;
    
    Book(String titulo, String autor, String capa, BookGenre Genre, Integer anoPublicacao){
        this.titulo = titulo;
        this.autor = autor;
        this.capa = capa;
        this.Genre = Genre;
        this.anoPublicacao = anoPublicacao;
    }
    
    protected Book(){}

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public String getCapa() {
        return capa;
    }

    public BookGenre getGenre() {
        return Genre;
    }

    public Long getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
}