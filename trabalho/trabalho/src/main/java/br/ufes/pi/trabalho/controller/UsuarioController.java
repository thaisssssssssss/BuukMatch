package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController{
    @Autowired
    private UsuarioRepository usuarioRepository;

    /*
     * Cadastra um novo Usuario
     * URL no postman: POST http://localhost:8080/cadastrar
     * json:
     * {
     *  "nome": "ExemploNome",
     *  "email": "exemplo@gmail.com",
     *  "senha": "senhaexemplo"
     * }
     * */
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        //salva um usuario no banco de dados
        return usuarioRepository.save(usuario);
    }

    /*
     * Possivel "login" de um Usuario
     * URL no postman: POST http://localhost:8080/login/exemplo@email.com/senhaexemplo
     * */
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

    /*
     * Retorna lista com todos os usuarios
     * URL no postman: GET http://localhost:8080/usuarios
     * */
    @GetMapping("/usuarios")
    public Iterable<Usuario> getChats() {
        return usuarioRepository.findAll();
    }
}

