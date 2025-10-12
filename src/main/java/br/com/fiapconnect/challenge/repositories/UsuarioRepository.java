package br.com.fiapconnect.challenge.repositories;

import br.com.fiapconnect.challenge.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByRm(String rm);
    List<Usuario> findByTurma(String turma);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}