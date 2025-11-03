package br.com.fiapconnect.challenge.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_do_projeto", nullable = false)
    private String nomeDoProjeto;
    @Column(nullable = false)
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeDoProjeto() { return nomeDoProjeto; }
    public void setNomeDoProjeto(String nomeDoProjeto) { this.nomeDoProjeto = nomeDoProjeto; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
