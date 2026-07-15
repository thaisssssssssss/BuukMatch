package br.ufes.pi.trabalho.service;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.User;
import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.MatchRepository;


@Service
public class MatchService {
    private final  MatchRepository matchRepository;
    private final ChatRepository chatRepository;
    private final NotificationService notificationService;

    public MatchService(MatchRepository matchRepository, ChatRepository chatRepository, NotificationService notificationService){
        this.matchRepository = matchRepository;
        this.chatRepository = chatRepository;
        this.notificationService = notificationService;
    }

    public Match registrar(User user1 , User user2){
        Match match = new Match(user1, user2); 
        // user1.addMatch(match);
        // user2.addMatch(match);

        Match savedMatch = this.matchRepository.save(match);
    
        Chat chat = new Chat(savedMatch);
        chatRepository.save(chat);

        notificationService.registerMatchNotificationOwner(savedMatch);

        return savedMatch;
    }
}
