package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.dto.LoginRequest;
import br.ufes.pi.trabalho.dto.CreateUserRequest;
import br.ufes.pi.trabalho.dto.UserResponse;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.UserRepository;
import br.ufes.pi.trabalho.service.UserService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController 
@RequestMapping("/users")
public class UserController{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Cadastra um novo usuário
     *<br><br>

     * URL no postman: POST http://localhost:8080/user
     *<br><br>

     * JSON:
     * {{
     *  "nome": "Marina",
     *  "email": "marina@gmail.com",
     *  "senha": "123456",
     *  "dataNascimento": "2004-05-20",
     *  "Address": {
     *    "rua": "Rua A",
     *    "cidade": "Vitória",
     *    "bairro": "Centro",
     *    "numero": 10
     *  }}
     *   
     * @param CreateUserRequest informações do usuário que se deseja cadastrar
     * @return usuário cadastrado e salvo no banco de dados
     */
    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody CreateUserRequest request) {
        //salva um User no banco de dados
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserResponseById(id));
    }

    /**
     * Realiza um possivel "login" de um usuário
     *<br><br>
     
     * URL no postman: POST http://localhost:8080/login/exemplo@email.com/senhaexemplo
     *
     * @param email e-mail do usuário a ser cadastrado
     * @param senha senha do usuário a ser cadastrado
     * @return usuário cujo login foi realizado
     */
    @PostMapping("/login")  
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest login){
        return ResponseEntity.ok(userService.login(login));
    }

    /**
     * URL no postman: GET http://localhost:8080/users
     *
     * @return lista com todos os usuários
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @PostMapping("/{id}/messages") // como organizar por chat entre dois Users especificamente e nao so enviar todas mensagens
    public ResponseEntity<List<Message>> listMessages(@PathVariable Long id){
        return ResponseEntity.ok(userService.listMessages(id));
    }
}

