package br.com.fiapconnect.challenge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NonNull

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rm;
    private String nome;
    private String turma;
    }
