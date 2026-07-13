package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.ufes.pi.trabalho.dto.ChatResponse;
import br.ufes.pi.trabalho.dto.MessageResponse;
import br.ufes.pi.trabalho.dto.SendMessageRequest;
import br.ufes.pi.trabalho.service.ChatService;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatController chatController;

    @Test
    void listUserChatsSuccess() {
        ChatResponse chat = new ChatResponse(10L);
        chat.setName("João");
        chat.setLastMessage("Última mensagem");

        when(chatService.listUserChats("token123")).thenReturn(List.of(chat));

        ResponseEntity<List<ChatResponse>> response = chatController.listUserChats("token123");

        assertEquals(200, response.getStatusCode().value());
    
        // confirma que o controler chamou o metodo do service
        verify(chatService).listUserChats("token123");
    }

    @Test
    void listMessageSuccess() {
        MessageResponse msg = new MessageResponse(
                2L,
                LocalDateTime.now(),
                "Oi",
                "Maria",
                true
        );

        when(chatService.listMessage(10L, "token123")).thenReturn(List.of(msg));

        ResponseEntity<List<MessageResponse>> response = chatController.listMessage(10L, "token123");

        assertEquals(200, response.getStatusCode().value());
        
        // confirma que o controler chamou o metodo do service
        verify(chatService).listMessage(10L, "token123");
    }

    @Test
    void sendMessageSuccess() {
        SendMessageRequest request = new SendMessageRequest();
        request.setConteudo("Oi, tudo bem?");

        ResponseEntity<MessageResponse> response = chatController.sendMessage(10L, request, "token123");

        assertEquals(201, response.getStatusCode().value());

        // confirma que o controler chamou o metodo do service
        verify(chatService).sendMessage(10L, "token123", "Oi, tudo bem?");
    }
}