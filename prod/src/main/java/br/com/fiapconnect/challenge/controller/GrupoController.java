package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GrupoController {
    private final GrupoService service;

    public GrupoController(GrupoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GrupoOutDto> criar(@RequestBody GrupoInDto in) {
        var salvo = service.criar(in);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<GrupoOutDto>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false, name = "nome") String nome) {
        return ResponseEntity.ok(service.listar(status, nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoOutDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
