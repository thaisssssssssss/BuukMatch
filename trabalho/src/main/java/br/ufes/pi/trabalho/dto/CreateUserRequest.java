package br.ufes.pi.trabalho.dto;

import java.time.LocalDate;

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthdate;
    private AddressRequest address;

    public CreateUserRequest(String name, String email, String password, LocalDate birthdate, AddressRequest address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
    }

    public CreateUserRequest(){}

    public AddressRequest getAddress() {
        return address;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

}
