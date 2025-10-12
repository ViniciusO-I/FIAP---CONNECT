package br.com.fiapconnect.challenge.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "GRUPO")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME_DO_PROJETO", nullable = false)
    private String nomeDoProjeto;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @ManyToMany
    @JoinTable(
            name = "GRUPO_USUARIO",
            joinColumns = @JoinColumn(name = "GRUPO_ID"),
            inverseJoinColumns = @JoinColumn(name = "USUARIO_ID")
    )
    private List<Usuario> usuarios = new ArrayList<>();
    public Long getId() {
        return id;
    }
    public String getNomeDoProjeto() {
        return nomeDoProjeto;
    }
    public String getStatus() {
        return status;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNomeDoProjeto(String nomeDoProjeto) {
        this.nomeDoProjeto = nomeDoProjeto;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;

    }
}