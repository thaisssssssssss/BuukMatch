package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.CrudRepository;
import br.ufes.pi.trabalho.domain.Postagem;
public interface PostagemRepository extends CrudRepository<Postagem, Long>{
    
}
