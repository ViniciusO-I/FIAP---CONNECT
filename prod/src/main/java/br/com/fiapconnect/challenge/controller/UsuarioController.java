package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioOutDto> criar(@RequestBody UsuarioInDto in) {
        var salvar = service.criar(in);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutDto>> listar(
            @RequestParam(required = false) String turma,
            @RequestParam(required = false, name = "nome") String nome) {
        return ResponseEntity.ok(service.listar(turma, nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
