package br.ufes.pi.trabalho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.pi.trabalho.dto.NotificationResponse;
import br.ufes.pi.trabalho.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> listNotificationsNotRead(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(notificationService.listNotificationsNotRead(token));
    }
}
