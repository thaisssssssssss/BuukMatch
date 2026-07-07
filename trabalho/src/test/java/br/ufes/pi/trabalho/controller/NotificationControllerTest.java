package br.ufes.pi.trabalho.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.service.NotificationService;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void listNotificationsNotReadSuccess() {
        NotificationResponse n1 = new NotificationResponse("Ebaaa, você deu match com João!");

        when(notificationService.listNotificationsNotRead("token123")).thenReturn(List.of(n1));

        ResponseEntity<List<NotificationResponse>> response = notificationController.listNotificationsNotRead("token123");

        assertEquals(200, response.getStatusCode().value());
        
        // confirma que o controler chamou o metodo do service
        verify(notificationService).listNotificationsNotRead("token123");
    }
}
