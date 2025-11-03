package br.com.fiapconnect.challenge.dto;

public class UsuarioOutDto {
    private Long id;
    private String rm;
    private String nome;
    private String turma;
    public UsuarioOutDto() {}
    public UsuarioOutDto(Long id, String rm, String nome, String turma) {
        this.id = id; this.rm = rm; this.nome = nome; this.turma = turma;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRm() { return rm; }
    public void setRm(String rm) { this.rm = rm; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }
}
