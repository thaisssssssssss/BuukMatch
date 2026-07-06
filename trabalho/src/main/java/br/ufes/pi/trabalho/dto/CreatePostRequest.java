package br.ufes.pi.trabalho.dto;


public class CreatePostRequest{
    private String description;
    private String photo;
    
    public CreatePostRequest(String description, String photo){
        this.description = description;
        this.photo = photo;
    }

    protected CreatePostRequest(){}
    
    public String getDescription() {
        return description;
    }
    
    public String getPhoto() {
        return photo;
    }
}