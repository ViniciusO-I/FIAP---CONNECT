package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<?> processar(@RequestBody RequisicaoUsuario req) {
        try {
            return switch (req.getAcao()) {
                case "criar"   -> ResponseEntity.status(201).body(service.criar(req.getUsuario()));
                case "listar"  -> ResponseEntity.ok(service.listar(req.getTurma(), req.getNome()));
                case "buscar"  -> ResponseEntity.ok(service.buscarPorId(req.getId()));
                case "deletar" -> { service.deletar(req.getId()); yield ResponseEntity.noContent().build(); }
                default        -> ResponseEntity.badRequest().body("acao invalida");
            };
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    public static class RequisicaoUsuario {
        private String acao;
        private Long id;
        private String turma;
        private String nome;
        private UsuarioInDto usuario;

        public String getAcao() {
            return acao;

        }
        public Long getId() {
            return id;
        }
        public String getTurma() {
            return turma;
        }
        public String getNome() {
            return nome;
        }
        public UsuarioInDto getUsuario() {
            return usuario;
        }
        public void setAcao(String acao) {
            this.acao = acao;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public void setTurma(String turma) {
            this.turma = turma;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public void setUsuario(UsuarioInDto usuario) {
            this.usuario = usuario;
        }
    }
}