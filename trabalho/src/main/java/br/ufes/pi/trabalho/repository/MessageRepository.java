package br.ufes.pi.trabalho.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findByChatOrderByDataRecebimentoAsc(Chat chat);
}
