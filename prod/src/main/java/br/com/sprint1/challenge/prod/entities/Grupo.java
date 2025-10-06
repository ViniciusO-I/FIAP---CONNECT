package br.com.sprint1.challenge.prod.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NonNull

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDoProjeto;
    private String status;

    @ManyToMany
    private List<Usuario> usuarios;
}
