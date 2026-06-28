package br.ufes.pi.trabalho.dto;


/// cada request de alguma coisa tem que ter alguma lista associada
/// fiz essa aqui so para testar
/// é que nao é certo enviar o objeto usuario como um todo para o front-end
public class UserResponse {
    private String name;
    private String email;
    private String photo;

    public UserResponse(String name, String email, String photo){
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    
    public String getPhoto() {
        return photo;
    }

    // foto

}
