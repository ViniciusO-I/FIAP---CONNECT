package br.com.fiapconnect.challenge.repositories;

import br.com.fiapconnect.challenge.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByStatusIgnoreCase(String status);
    List<Grupo> findByNomeDoProjetoContainingIgnoreCase(String nome);


}
