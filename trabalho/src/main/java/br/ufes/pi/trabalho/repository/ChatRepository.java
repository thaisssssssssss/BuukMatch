package br.ufes.pi.trabalho.repository;

import br.ufes.pi.trabalho.domain.Chat;
import br.ufes.pi.trabalho.domain.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    Optional<Chat> findByMatchId(Long matchId);

    List<Chat> findByUser1OrUser2(User user1, User user2);
}
