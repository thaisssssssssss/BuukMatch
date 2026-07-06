package br.ufes.pi.trabalho.dto;
// package src.main.java.br.ufes.pi.trabalho.dto;

public class LikeRequest {
    private Long idPost;

    public LikeRequest(Long idPost){
        this.idPost = idPost;
    }

    protected LikeRequest(){}
    
    public Long getIdPost() {
        return idPost;
    }
}
