package br.ufes.pi.trabalho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Match;
import br.ufes.pi.trabalho.domain.Message;
import br.ufes.pi.trabalho.domain.User;

import br.ufes.pi.trabalho.repository.ChatRepository;
import br.ufes.pi.trabalho.repository.MatchRepository;
import br.ufes.pi.trabalho.repository.MessageRepository;
import br.ufes.pi.trabalho.repository.UserRepository;

@Service 
public class ChatService {
    private final ChatRepository chatRepository;
    private final MatchRepository matchRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, MatchRepository matchRepository, MessageRepository messageRepository, UserRepository userRepository){
        this.chatRepository = chatRepository;
        this.matchRepository = matchRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Chat creatMatchChat(Long matchId){
        Match match = matchRepository
                    .findById(matchId)
                    .orElseThrow(() -> new RuntimeException("Match nao encontrado"));

        return chatRepository
               .findByMatchId(matchId)
               .orElseGet(() -> chatRepository.save(new Chat(match)));
    }

    public List<Chat> listUserChats(Long userId){
        User user = userRepository
                    .findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        return chatRepository
               .findByUser1OrUser2(user, user);
    }

    public List<Message> listMessage(Long chatId){
        Chat chat = chatRepository
                    .findById(chatId)
                    .orElseThrow(() -> new RuntimeException("Chat nao encontrado"));

        return messageRepository
               .findByChatOrderByDataRecebimentoAsc(chat);
    }

    public Message sendMessage(Long chatId, Long remetenteId, String conteudo){
        Chat chat = chatRepository
                    .findById(chatId)
                    .orElseThrow(() -> new RuntimeException("Chat nao encontrado"));

        User remetente = userRepository
                         .findById(remetenteId)
                         .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        if(!chat.getUser1().equals(remetente) && !chat.getUser2().equals(remetente)){
            throw new RuntimeException("Usuario nao esta nesse chat");
        }

        Message msg = new Message(conteudo, remetente, chat);

        return messageRepository.save(msg);
    }

}
