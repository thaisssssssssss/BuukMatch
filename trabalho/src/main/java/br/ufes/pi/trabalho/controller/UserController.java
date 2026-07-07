package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.dto.LoginRequest;
import br.ufes.pi.trabalho.dto.LoginResponse;
import br.ufes.pi.trabalho.dto.CreateUserRequest;
import br.ufes.pi.trabalho.dto.UserResponse;
import br.ufes.pi.trabalho.service.UserService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
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

     * URL no postman: POST http://localhost:8080/users
     *<br><br>

     * JSON:
     * {
     *      "name": "Maria",
     *      "email": "maria@gmail.com",
     *      "password": "123456",
     *      "birthdate": "2004-05-20",
     *      "address": {
     *          "street": "Rua A",
     *          "city": "Vitória",
     *          "district": "Centro",
     *          "number": 10
     *      }
     *  }
     *   
     * @param request informações do usuário que se deseja cadastrar
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
     * Realiza o login de um usuário
     *<br><br>
     
     * URL no postman: POST http://localhost:8080/users/login
     *
     * JSON:
     * {
     *      "email": "luisa@gmail.com",
     *      "password": "123123"
     *  }
     * @param login email e senha de um usuario
     * @return token de cadastro
     */
    @PostMapping("/login")  
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest login){
        return ResponseEntity.ok(userService.login(login));
    }

    /**
     * URL no postman: GET http://localhost:8080/users
     *
     * @return lista com todos os usuários
     */
    @GetMapping("/listar")
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

}

