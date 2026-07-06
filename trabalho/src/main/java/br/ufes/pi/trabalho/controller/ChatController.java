package br.ufes.pi.trabalho.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.pi.trabalho.dto.ChatResponse;
import br.ufes.pi.trabalho.dto.MessageResponse;
import br.ufes.pi.trabalho.dto.SendMessageRequest;
import br.ufes.pi.trabalho.service.ChatService;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ChatResponse>> listUserChats(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(chatService.listUserChats(token));
    }

    @GetMapping("/{chatId}/mensagens/listar")
    public ResponseEntity<List<MessageResponse>> listMessage(@PathVariable Long chatId, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(chatService.listMessage(chatId, token));
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
    @PostMapping("/{chatId}/mensagens/enviar")
    public ResponseEntity<Void> sendMessage(@PathVariable Long chatId, @RequestBody SendMessageRequest request, @RequestHeader("Authorization") String token){
        chatService.sendMessage(chatId, token, request.getConteudo());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
