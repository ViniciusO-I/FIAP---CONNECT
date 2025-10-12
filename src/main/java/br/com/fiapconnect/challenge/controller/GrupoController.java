package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    private final GrupoService service;
    public GrupoController(GrupoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<?> processar(@RequestBody RequisicaoGrupo req) {
        try {
            return switch (req.getAcao()) {
                case "criar"   -> ResponseEntity.status(201).body(service.criar(req.getGrupo()));
                case "listar"  -> ResponseEntity.ok(service.listar(req.getStatus(), req.getNomeDoProjeto()));
                case "buscar"  -> ResponseEntity.ok(service.buscarPorId(req.getId()));
                case "deletar" -> { service.deletar(req.getId()); yield ResponseEntity.noContent().build(); }
                default        -> ResponseEntity.badRequest().body("acao invalida");
            };
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    public static class RequisicaoGrupo {
        private String acao;
        private Long id;
        private String status;
        private String nomeDoProjeto;
        private GrupoInDto grupo;

        public String getAcao() {
            return acao;
        }
        public Long getId() {
            return id;
        }
        public String getStatus() {
            return status;
        }
        public String getNomeDoProjeto() {
            return nomeDoProjeto;
        }
        public GrupoInDto getGrupo() {
            return grupo;
        }
        public void setAcao(String acao) {
            this.acao = acao;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public void setNomeDoProjeto(String nomeDoProjeto) {
            this.nomeDoProjeto = nomeDoProjeto;
        }
        public void setGrupo(GrupoInDto grupo) {
            this.grupo = grupo;


        }
    }
}