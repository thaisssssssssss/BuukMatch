package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.dto.LoginData;
import br.ufes.pi.trabalho.domain.Mensagem;
import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.UsuarioRepository;
import br.ufes.pi.trabalho.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/usuarios")
public class UsuarioController{
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
     * Cadastra um novo usuário
     *<br><br>

     * URL no postman: POST http://localhost:8080/cadastrar
     *<br><br>

     * JSON:
     * {{
     *  "nome": "Marina",
     *  "email": "marina@gmail.com",
     *  "senha": "123456",
     *  "dataNascimento": "2004-05-20",
     *  "endereco": {
     *    "rua": "Rua A",
     *    "cidade": "Vitória",
     *    "bairro": "Centro",
     *    "numero": 10
     *  }}
     *   
     * @param usuario informações do usuário que se deseja cadastrar
     * @return usuário cadastrado e salvo no banco de dados
     */
    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        //salva um usuario no banco de dados
        return usuarioService.cadastrar(usuario);
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
    public Usuario loginUsuario(LoginData login){
        return usuarioService.login(login);
    }

    /**
     * URL no postman: GET http://localhost:8080/usuarios
     *
     * @return lista com todos os usuários
     */
    @GetMapping
    public Iterable<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/{id}/mensagens") // como organizar por chat entre dois usuarios especificamente e nao so enviar todas mensagens
    public Iterable<Mensagem> listarMensagens(@PathVariable Long id){
        return usuarioService.listarMensagens(id);
    }

    // @PostMapping("/{id}/post")
    // public Postagem publicarPostagem(){
    //     ret
    // }

}

