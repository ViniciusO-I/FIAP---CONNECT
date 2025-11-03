package br.com.fiapconnect.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioInDto {
    @NotBlank private String rm;
    @NotBlank private String nome;
    @NotBlank private String turma;
    public String getRm() { return rm; }
    public void setRm(String rm) { this.rm = rm; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }
}
