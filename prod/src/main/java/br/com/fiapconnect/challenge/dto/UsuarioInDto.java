package br.com.fiapconnect.challenge.dto;

import lombok.Getter;
import lombok.Setter;

// dados de entrada para criar usuario
@Getter
@Setter
public class UsuarioInDto {
    private String rm;
    private String nome;
    private String turma;
}
