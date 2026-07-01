package br.ufes.pi.trabalho.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.dto.SendMessageRequest;
import br.ufes.pi.trabalho.service.ChatService;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/match/{matchId}")
    public Chat creatMatchChat(@PathVariable Long matchId){
        return chatService.creatMatchChat(matchId);
    }

    @GetMapping("/usuario/{userId}")
    public List<Chat> listUserChats(@PathVariable Long userId){
        return chatService.listUserChats(userId);
    }

    @GetMapping("/{chatId}/mensagens/listar")
    public List<Message> listMessage(@PathVariable Long chatId, @RequestBody SendMessageRequest request){
        return chatService.listMessage(chatId);
    }

    /**
     * Envia uma mensagem em um chat aberto
     *<br><br>
     * 
     * URL no postman: GET http://localhost:8080/chats/1/mensagens/enviar
     * 
     * No campo headers coloca Authorization e o token recebido ao fazer login.
     * JSON:
     * {
     *      "conteudo": "oi"
     *  }
     */
    @GetMapping("/{chatId}/mensagens/enviar")
    public Message sendMessage(@PathVariable Long chatId, @RequestBody SendMessageRequest request, @RequestHeader("Authorization") String token){
        return chatService.sendMessage(chatId, token, request.getConteudo());
    }
}
