package br.ufes.pi.trabalho.dto;


public class CreatePostRequest{
    private Long idUser;
    private String description;
    private String photo;
    
    public CreatePostRequest(String description, String photo, Long idUser){
        this.description = description;
        this.photo = photo;
        this.idUser = idUser;
    }

    protected CreatePostRequest(){}
    
    public String getDescription() {
        return description;
    }

    public Long getIdUser() {
        return idUser;
    }
    
    public String getPhoto() {
        return photo;
    }
}