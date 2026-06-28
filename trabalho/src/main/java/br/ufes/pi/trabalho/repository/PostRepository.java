package br.ufes.pi.trabalho.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;

public interface PostRepository extends CrudRepository<Post, Long>{
    List<Post> findByOwner(User owner);
}
