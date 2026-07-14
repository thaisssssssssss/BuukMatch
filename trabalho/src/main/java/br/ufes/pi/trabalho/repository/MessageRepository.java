package br.ufes.pi.trabalho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findByChatOrderByDataRecebimentoAsc(Chat chat);

    List<Message> findByChatOrderByIdAsc(Chat chat);
    
    List<Message> findByChatAndIdGreaterThanOrderByIdAsc(Chat chat, Long lastMsgId);

    Optional<Message> findFirstByChatOrderByIdDesc(Chat chat);
}
