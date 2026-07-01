package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.Token;

public interface TokenRepository extends CrudRepository<Token, String>{
    
}
