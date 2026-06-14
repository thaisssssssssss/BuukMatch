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

    /**
     * Cria um novo chat para um usuário já cadastrado.
     *<br><br>

     * URL no Postman: POST http://localhost:8080/chat/exemplo@gmail.com
     *<br><br>

     * JSON:
     * {
     *   "receptor": "Exemplo"
     * }
     *
     * @param email e-mail do usuário cadastrado
     * @param chat dados do chat recebido no corpo da requisição
     * @return chat criado e salvo no banco de dados, ou null caso o usuário não exista
     */
    @PostMapping("/chat/{email}")
    public Chat criaChat(@PathVariable String email,
                         @PathVariable String calcinha,
                         @RequestBody Chat chat) {

        //pega o usuario do banco de dados (encontra pelo email)
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if(usuario == null) {
            return null;
        }
        chat.setUsuario(usuario);
        return chatRepository.save(chat);
    }

    /**
     * Adiciona novas mensagens em um chat já criado de um usuário já cadastrado
     *<br><br>

     * URL no postman: POST http://localhost:8080/chat/ExemploReceptor/exemplo@gmail.com/texto
     *<br><br>
     
     * JSON:
     * {
     *   "conteudo": "ExemploMensagem"
     * }
     *
     * @param email e-mail do usuário cadastrado
     * @param receptor nome do receptor da mensagem 
     * @param mensagem corpo da mensagem via JSON
     * @return chat atualizado e salvo no banco de dados
     */
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

    /**
     * URL no postman: GET http://localhost:8080/chat/exemplo@gmail.com
     *
     *
     * @param email e-mail do usuário cadastrado
     * @return Retorna todos os chats abertos de um usuário já cadastrado
     */
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