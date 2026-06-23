package br.ufes.pi.trabalho.domain;

public class Like{
    Usuario usuario;
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