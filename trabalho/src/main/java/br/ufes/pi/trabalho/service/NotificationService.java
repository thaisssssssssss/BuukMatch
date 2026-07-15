package br.ufes.pi.trabalho.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.Notification;
import br.ufes.pi.trabalho.repository.NotificationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public NotificationService(NotificationRepository notificationRepository, UserService userService){
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    public NotificationResponse createMatchNotificationUser(Match match){
        String msg = "Ebaaa, você deu match com " + match.getUser2().getName() + "!";
        
        return (new NotificationResponse(msg));
    }

    public void registerMatchNotificationOwner(Match match){
        String msg = "Ebaaa, você deu match com " + match.getUser1().getName() + "!";
        
        Notification notification = new Notification(match.getUser2(), match, msg);

        notificationRepository.save(notification);
    }

    public NotificationResponse creatWaitingMatchNotification(){
        String msg = "Aguardando um futuro match...";
        return (new NotificationResponse(msg));
    }
    
    @Transactional
    public List<NotificationResponse> listNotificationsNotRead(String token){
        User user = userService.returnUserByToken(token);

        List<Notification> notifications = notificationRepository.findByUserAndReadFalse(user);
        
        List<NotificationResponse> response = new ArrayList<>();

        for(Notification n : notifications){
            n.setRead();
            System.out.println(n);
            response.add(new NotificationResponse(n.getMessage()));
        }

        return response;
    }
}
