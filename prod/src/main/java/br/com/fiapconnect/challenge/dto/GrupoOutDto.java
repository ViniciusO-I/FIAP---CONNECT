package br.com.fiapconnect.challenge.dto;

import lombok.Getter;
import lombok.Setter;

//dado saida do grupo
@Getter
@Setter
public class GrupoOutDto {
    public Long id;
    private String nomeDoProjeto;
    private String status;
}
