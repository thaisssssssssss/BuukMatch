package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.ufes.pi.trabalho.dto.LoveRequest;
import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.service.LoveService;

@ExtendWith(MockitoExtension.class)
class LoveControllerTest {

    @Mock
    private LoveService loveService;

    @InjectMocks
    private LoveController loveController;

    @Test
    void registerLoveOnPostSuccess() {
        LoveRequest request = new LoveRequest();
        request.setIdPost(1L);

        NotificationResponse notification = new NotificationResponse("Ebaaa, você deu match com João!");

        when(loveService.registerLoveOnPost(request, "token123")).thenReturn(notification);

        ResponseEntity<NotificationResponse> response = loveController.registerLoveOnPost(request, "token123");

        assertEquals(200, response.getStatusCode().value());
        
        // confirma que o controler chamou o metodo do service
        verify(loveService).registerLoveOnPost(request, "token123");
    }
}