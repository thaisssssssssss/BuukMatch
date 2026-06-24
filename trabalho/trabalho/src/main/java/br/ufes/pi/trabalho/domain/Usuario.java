package br.ufes.pi.trabalho.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String foto;
    private LocalDate dataNascimento;
    private ArrayList<GeneroLivro> generosFavoritos; 
    private ArrayList<Postagem> postagems;
    private Endereco endereco;
    private ArrayList<Match> matches;  

    protected Usuario(){}

    public Usuario(String nome, String email, String senha, LocalDate dataNascimento, Endereco endereco){
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.matches = new ArrayList<Match>();
        this.postagems = new ArrayList<Postagem>();
        this.generosFavoritos = new ArrayList<GeneroLivro>();

    }

    public void adiciona_match(Match match){
        this.matches.add(match);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean comparaSenha(String senha){
//        if(!this.senha.equals(senha)) throw new Exception("Senha incorreta");
        if(!this.senha.equals(senha)) return false;
        else return true;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getFoto() {
        return foto;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public ArrayList<Match> getMatches() {
        return matches;
    }
    public ArrayList<Postagem> getPostagems() {
        return postagems;
    }

    public ArrayList<GeneroLivro> getGenerosFavoritos() {
        return generosFavoritos;
    }
}
