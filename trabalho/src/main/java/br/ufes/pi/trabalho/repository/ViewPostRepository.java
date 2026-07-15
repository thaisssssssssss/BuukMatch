package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.ViewPost;

public interface ViewPostRepository extends CrudRepository<ViewPost, Long>{
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
