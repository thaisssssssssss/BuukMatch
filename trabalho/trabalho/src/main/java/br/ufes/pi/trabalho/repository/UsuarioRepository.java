package br.ufes.pi.trabalho.repository;

import br.ufes.pi.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findUsuarioByEmail(String email);
}
