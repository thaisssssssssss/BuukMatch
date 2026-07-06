package br.ufes.pi.trabalho.dto;

public class LoveRequest {
    private Long idPost;

    
    public LoveRequest(Long idPost){
        this.idPost = idPost;
    }
    
    public LoveRequest(){}
    
    public Long getIdPost() {
        return idPost;
    }
    
    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
}
