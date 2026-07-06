package br.ufes.pi.trabalho.repository;

import br.ufes.pi.trabalho.domain.Love;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface LoveRepository extends CrudRepository<Love, Long> {
    boolean existsByUserAndPost(User user, Post post);
    // Love findLoveById(Long id);
    // Love findLoveById(Long id); // fazer um find by o nome do dono do post
}