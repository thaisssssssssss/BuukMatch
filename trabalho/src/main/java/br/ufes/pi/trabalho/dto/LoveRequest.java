package br.ufes.pi.trabalho.dto;

public class LoveRequest {
    private Long idPost;

    public LoveRequest(Long idUser, Long idPost){
        this.idPost = idPost;
    }

    protected LoveRequest(){}
    
    public Long getIdPost() {
        return idPost;
    }
}
