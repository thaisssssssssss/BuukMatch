package br.ufes.pi.trabalho.repository;

import br.ufes.pi.trabalho.domain.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
    // Like findLikeById(Long id);
    // Like findLikeById(Long id); // fazer um find by o nome do dono do post
}