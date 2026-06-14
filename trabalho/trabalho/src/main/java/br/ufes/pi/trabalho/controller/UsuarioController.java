package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController{
    @Autowired
    private UsuarioRepository usuarioRepository;

    
    /**
     * Cadastra um novo usuário
     *<br><br>

     * URL no postman: POST http://localhost:8080/cadastrar
     *<br><br>

     * JSON:
     * {
     *   "nome": "ExemploNome",
     *   "email": "exemplo@gmail.com",
     *   "senha": "senhaexemplo"
     * }
     *
     * @param usuario informações do usuário que se deseja cadastrar
     * @return usuário cadastrado e salvo no banco de dados
     */
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        //salva um usuario no banco de dados
        return usuarioRepository.save(usuario);
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
    @PostMapping("/login/{email}/{senha}")
    public Usuario loginUsuario(@PathVariable String email, @PathVariable String senha){

        //pega o usuario do banco de dados (encontra pelo email)
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);

        if (usuario == null) {
            return null;
        }

        //compara senhas (talvez lancar uma exception aqui)
        if(usuario.comparaSenha(senha)){
            return usuario;
        }
        else return null;
    }

    /**
     * URL no postman: GET http://localhost:8080/usuarios
     *
     * @return lista com todos os usuários
     */
    @GetMapping("/usuarios")
    public Iterable<Usuario> getChats() {
        return usuarioRepository.findAll();
    }
}

