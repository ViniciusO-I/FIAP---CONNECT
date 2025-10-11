package br.com.fiapconnect.challenge.dto;

import lombok.Getter;
import lombok.Setter;

// dado  saida do usuario
@Getter
@Setter
public class UsuarioOutDto {
    private Long id;
    private String rm;
    private String nome;
    private String turma;
}
