package br.com.fiapconnect.challenge.dto;

public class GrupoOutDto {
    private Long id;
    private String nomeDoProjeto;
    private String status;
    public GrupoOutDto() {}
    public GrupoOutDto(Long id, String nomeDoProjeto, String status) {
        this.id = id; this.nomeDoProjeto = nomeDoProjeto; this.status = status;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeDoProjeto() { return nomeDoProjeto; }
    public void setNomeDoProjeto(String nomeDoProjeto) { this.nomeDoProjeto = nomeDoProjeto; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
