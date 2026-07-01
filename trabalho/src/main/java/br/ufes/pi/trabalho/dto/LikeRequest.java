package br.ufes.pi.trabalho.dto;
// package src.main.java.br.ufes.pi.trabalho.dto;

public class LikeRequest {
    private Long idUser;
    private Long idPost;

    public LikeRequest(Long idUser, Long idPost){
        this.idPost = idPost;
        this.idUser = idUser;
    }

    protected LikeRequest(){}
    
    public Long getIdPost() {
        return idPost;
    }
    
    public Long getIdUser() {
        return idUser;
    }
}
