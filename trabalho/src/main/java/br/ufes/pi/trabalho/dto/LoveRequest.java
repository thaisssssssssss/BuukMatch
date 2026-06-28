package br.ufes.pi.trabalho.dto;

public class LoveRequest {
    private Long idUser;
    private Long idPost;

    public LoveRequest(Long idUser, Long idPost){
        this.idPost = idPost;
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }
    
    public Long getIdUser() {
        return idUser;
    }
}
