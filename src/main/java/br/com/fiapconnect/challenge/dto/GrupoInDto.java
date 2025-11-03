package br.com.fiapconnect.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public class GrupoInDto {
    @NotBlank private String nomeDoProjeto;
    @NotBlank private String status;
    public String getNomeDoProjeto() { return nomeDoProjeto; }
    public void setNomeDoProjeto(String nomeDoProjeto) { this.nomeDoProjeto = nomeDoProjeto; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
