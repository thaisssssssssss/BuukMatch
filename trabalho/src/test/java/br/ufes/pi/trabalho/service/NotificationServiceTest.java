package br.ufes.pi.trabalho.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.Notification;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.repository.NotificationRepository;

class NotificationServiceTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private NotificationService notificationService;


    @Test
    void createMatchNotificationSuccess() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        User joao = new User(
                "João",
                "joao@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        Match match = new Match(maria, joao);

        notificationService.creatMatch2Notification(maria, joao, match);

        ArgumentCaptor<Notification> captor =
                ArgumentCaptor.forClass(Notification.class);

        verify(notificationRepository).save(captor.capture());

        Notification notification = captor.getValue();

        assertEquals(maria, notification.getUser());
        assertEquals(match, notification.getMatch());
        assertEquals(
                "Ebaaa, você deu match com João!",
                notification.getMessage()
        );
    }

    @Test
    void createWaitingMatchNotificationSuccess() {
        NotificationResponse response = notificationService.creatWaitingMatchNotification();

        assertNotNull(response);
        assertEquals(
                "Aguardando um futuro match...",
                response.getMessage()
        );

        verify(notificationRepository, never()).save(any(Notification.class));
    }

    @Test
    void listNotificationsNotReadSuccess() {
        User maria = new User(
                "Maria",
                "maria@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        User joao = new User(
                "João",
                "joao@email.com",
                "123456",
                LocalDate.of(2000, 1, 1),
                null
        );

        Match match = new Match(maria, joao);

        Notification n1 = new Notification(
                maria,
                match,
                "Ebaaa, você deu match com João!"
        );

        Notification n2 = new Notification(
                maria,
                match,
                "Nova notificação de teste"
        );

        when(userService.returnUserByToken("tokenMaria")).thenReturn(maria);

        when(notificationRepository.findByUserAndReadFalse(maria)).thenReturn(List.of(n1, n2));

        List<NotificationResponse> response = notificationService.listNotificationsNotRead("tokenMaria");

        assertEquals(
                "Ebaaa, você deu match com João!",
                response.get(0).getMessage()
        );
        assertEquals(
                "Nova notificação de teste",
                response.get(1).getMessage()
        );

        verify(userService).returnUserByToken("tokenMaria");
        verify(notificationRepository).findByUserAndReadFalse(maria);
    }
}