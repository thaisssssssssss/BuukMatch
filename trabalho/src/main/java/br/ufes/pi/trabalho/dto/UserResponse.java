package br.ufes.pi.trabalho.dto;

/// cada request de alguma coisa tem que ter alguma lista associada
/// fiz essa aqui so para testar
/// é que nao é certo enviar o objeto usuario como um todo para o front-end
public class UserResponse {
    private String name;
    private String email;

    public UserResponse(String name, String email){
        this.email = email;
        this.name = name;
    }

    protected UserResponse(){}

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
