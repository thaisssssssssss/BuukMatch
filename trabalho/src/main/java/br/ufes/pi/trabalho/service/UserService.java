package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.dto.LoginRequest;
import br.ufes.pi.trabalho.dto.LoginResponse;
import br.ufes.pi.trabalho.dto.UserResponse;
import br.ufes.pi.trabalho.domain.Token;
import br.ufes.pi.trabalho.domain.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.TokenRepository;
import br.ufes.pi.trabalho.repository.UserRepository;
import br.ufes.pi.trabalho.dto.CreateUserRequest;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserService(UserRepository UserRepository, TokenRepository tokwRepository) {
        this.userRepository = UserRepository;
        this.tokenRepository = tokwRepository;
    }

    /**
     * Regras de negócio para registrar um novo usuário no banco de dados da aplicação.
     *<br><br>
     *
     * Confere se o email registrado já existe no banco de dados.
     * Depois, cria um usuário e salva-o no banco de dados.
     *
     * @param request um CreateUserRequest com as informações necessárias para o cadastro de um novo usuário
     */
    public void registerUser(CreateUserRequest request) {

        // futuras validações
        
        if(existeUserPorEmail(request.getEmail())){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, "Um usuario com esse email ja existe");
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
            () -> new RuntimeException("Email nao encontrado")
        );
    
    }

    public void deletarPorEmail(String email){
        userRepository.deleteByEmail(email);
    }

    /**
     * Regras de negócio da realização de login de um usuário
     *<br><br>
     *
     * Caso o email registrado exista no banco de dados, compara-se a senha.
     * Depois, gera-se um token para o usuário, desse modo, toda operação que ele fizer 
     * enquanto estiver "logado" solicitará esse token.
     * Salva o token no banco de dados.
     *
     * @param login um LoginRequest com senha e e-mail do usuário a ser cadastrado
     * @return um LoginResponse contendo o token do usuário
     */
    public LoginResponse login(LoginRequest login){
        User user = userRepository
                 .findByEmail(login.getEmail())
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha ou email incorretos"));

        if(!user.comparePassword(login.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha ou email incorretos");
        }

        String token = UUID.randomUUID().toString();

        tokenRepository.save(new Token(token, user));

        UserResponse userResponse = new UserResponse(user.getName(), user.getEmail());

        // devolve userResponse pra evitar devolver a entidade diretamente para o usuário
        return new LoginResponse(token, userResponse);
    }

    public User returnUserByToken(String tokenAuthentication){
        Token token = tokenRepository
                      .findById(tokenAuthentication)
                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido"));

        return token.getUser();
    }


    public UserResponse findUserResponseById(Long id){
        User u = userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "User do not exist"));

        return new UserResponse(u.getName(), u.getEmail());
    }

    /**
     * Regras de negócio para retornar a lista de usuários cadastrados
     *<br><br>
     *
     * Pega todos os usuários presentes no banco de dados, e os devolve como userResponse.
     *
     * @return uma lista de UserResponse contendo os usuários cadastrados
     */
    public List<UserResponse>  listUsers(){
        Iterable<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<UserResponse>();
        
        for(User u : users){
            responses.add(new UserResponse(
                u.getName(),
                u.getEmail()
            ));
        }
    
        return responses;
    }

}
