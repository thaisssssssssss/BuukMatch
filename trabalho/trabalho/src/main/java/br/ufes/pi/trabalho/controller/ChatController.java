package br.ufes.pi.trabalho.controller;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Mensagem;
import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    /*
     * Cria um novo chat para um usuario ja cadastrado
     * URL no postman: POST http://localhost:8080/chat/exemplo@gmail.com
     * json:
     * {
     *   "receptor": "Exemplo"
     * }
     * */
    @PostMapping("/chat/{email}")
    public Chat criaChat(@PathVariable String email,
                         @RequestBody Chat chat) {

        //pega o usuario do banco de dados (encontra pelo email)
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if(usuario == null) {
            return null;
        }
        chat.setUsuario(usuario);
        return chatRepository.save(chat);
    }

    /*
     * Adiciona novas mensagens em um chat ja criado de um usuario ja cadastrado
     * URL no postman: POST http://localhost:8080/chat/ExemploReceptor/exemplo@gmail.com/texto
     * json:
     * {
     *   "conteudo": "ExemploMensagem"
     * }
     * */
    @PostMapping("/chat/{receptor}/{email}/texto")
    public Chat adicionaMensagemChatUsuario(@PathVariable String receptor,
                                            @PathVariable String email,
                                            @RequestBody Mensagem mensagem) {

        //pega o usuario do banco de dados (encontra pelo email)
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if(usuario == null) {
            return null;
        }

        //pega o chat do banco de dados (encontra pelo receptor e Usuario ativo)
        Chat chat = chatRepository.findChatByReceptorAndUsuario(receptor, usuario);
        if (chat == null) {
            return null;
        }

        chat.setMensagem(mensagem);
        return chatRepository.save(chat);
    }

    /*
     * Retorna todos os chats abertos de um usuario ja cadastrado
     * URL no postman: GET http://localhost:8080/chat/exemplo@gmail.com
     * */
    @GetMapping("/chat/{email}")
    public Iterable<Chat> getChats(@PathVariable String email) {

        //pega o usuario do banco de dados (encontra pelo email)
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if(usuario == null) {
            return null;
        }

        return chatRepository.findChatByUsuario(usuario);
    }

}