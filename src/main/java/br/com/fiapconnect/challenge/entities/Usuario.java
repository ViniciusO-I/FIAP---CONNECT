package br.com.fiapconnect.challenge.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String rm;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String turma;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRm() { return rm; }
    public void setRm(String rm) { this.rm = rm; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }
}
