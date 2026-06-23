package br.ufes.pi.trabalho.service;

import br.ufes.pi.trabalho.domain.LoginData;
import br.ufes.pi.trabalho.domain.Mensagem;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {

        // futuras validações

        return usuarioRepository.save(usuario);
    }

    public Usuario login(LoginData login){
        Usuario usuario = usuarioRepository.findUsuarioByEmail(login.getEmail());

        if (usuario == null) {
            return null;
        }
        //compara senhas (talvez lancar uma exception aqui)
        if(usuario.comparaSenha(login.getSenha())){
            return usuario;
        }
        else return null;
    }

    public Iterable<Usuario>  listarUsuarios(){
        return usuarioRepository.findAll();
    }
    public Iterable<Mensagem> listarMensagens(Long id){
        Usuario u = usuarioRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Usuario não existe"));
        

        // precisa retornar da melhor forma, 
        // a lista de chats!!!
        // return ;
    }
}
