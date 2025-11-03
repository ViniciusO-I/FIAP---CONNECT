package br.com.fiapconnect.challenge.repositories;

import br.com.fiapconnect.challenge.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByRm(String rm);
}
