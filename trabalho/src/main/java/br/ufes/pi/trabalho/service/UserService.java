package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.dto.LoginRequest;
import br.ufes.pi.trabalho.dto.UserResponse;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.domain.Address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.UserRepository;
import br.ufes.pi.trabalho.dto.CreateUserRequest;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    public void registerUser(CreateUserRequest request) {

        // futuras validações
        
        if(existeUserPorEmail(request.getEmail())){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, "A user with this email already exists");
        }
        Address address_new = new Address(request.getAddress().getStreet(), request.getAddress().getCity(), request.getAddress().getDistrict(), request.getAddress().getNumber() );
        User u_new = new User(request.getName(), request.getEmail(), request.getPassword(), request.getBirthdate(), address_new);
        
        userRepository.save(u_new);
    }

    public boolean existeUserPorEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("Email não encontrado")
        );
    
    }

    public void deletarPorEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserResponse login(LoginRequest login){
        // se o email estiver errado
        // se a senha estiver errada
        // o correto é enviar que um dos dois está errado
        // nao que um ou outro está errado
        User u = userRepository
        .findByEmail(login.getEmail())
        .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.UNAUTHORIZED, "Password or Email incorrect"));

        
        //compara senhas (talvez lancar uma exception aqui)
        if(!u.comparePassword(login.getPassword())){
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Password or Email incorrect"
            );
        }

        UserResponse u_resp = new UserResponse(u.getName(), u.getEmail(), u.getPhoto()
        );

        return u_resp;
    }

    public UserResponse findUserResponseById(Long id){
        User u = userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "User do not exist"));

        return new UserResponse(u.getName(), u.getEmail(), u.getPhoto());
    }

    public List<UserResponse>  listUsers(){
        Iterable<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<UserResponse>();
        
        for(User u : users){
            responses.add(new UserResponse(
                u.getName(),
                u.getEmail(),
                u.getPhoto()
            ));
        }
    
        return responses;
    }
    public List<Message> listMessages(Long id){
        User u = userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "User não existe"));
        

        // precisa retornar da melhor forma, 
        // precisa retornar as messagens, nao sei se por meio de um  DTO, se tiver algo sensivel la
        // a lista de chats!!!
        return null;
    }
}
