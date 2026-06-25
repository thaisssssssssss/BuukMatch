package br.ufes.pi.trabalho.repository;

import br.ufes.pi.trabalho.domain.User;

import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    @Transactional
    void deleteByEmail(String email);
    boolean existsByEmail(String email);
}
