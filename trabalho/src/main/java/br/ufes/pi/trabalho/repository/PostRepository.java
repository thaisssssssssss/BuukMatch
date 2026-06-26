package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.CrudRepository;
import br.ufes.pi.trabalho.domain.Post;
public interface PostRepository extends CrudRepository<Post, Long>{
    
}
