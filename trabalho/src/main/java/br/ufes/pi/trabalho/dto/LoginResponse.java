package br.ufes.pi.trabalho.dto;

public class LoginResponse {
    private String token;
    private UserResponse user;
    
    public LoginResponse(String token, UserResponse user){
        setToken(token);
        setUser(user);
    }

    protected LoginResponse(){}
    
    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
