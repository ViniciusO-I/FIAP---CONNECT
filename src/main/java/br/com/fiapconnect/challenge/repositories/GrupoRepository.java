package br.com.fiapconnect.challenge.repositories;

import br.com.fiapconnect.challenge.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    @Query("SELECT g FROM Grupo g WHERE (:nome is null or lower(g.nomeDoProjeto) like lower(concat('%', :nome, '%'))) "
         + "AND (:status is null or lower(g.status) = lower(:status))")
    List<Grupo> filtrar(@Param("nome") String nome, @Param("status") String status);
}
