package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.CrudRepository;
import br.ufes.pi.trabalho.domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{

    
}
