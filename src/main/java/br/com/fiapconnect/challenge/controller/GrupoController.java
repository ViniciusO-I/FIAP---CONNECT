package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService service;
    public GrupoController(GrupoService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public GrupoOutDto criar(@RequestBody @Valid GrupoInDto in) {
        return service.criar(in);
    }
    @GetMapping
    public List<GrupoOutDto> listar(@RequestParam(required=false) String nome,
                                    @RequestParam(required=false) String status) {
        if ((nome==null||nome.isBlank()) && (status==null||status.isBlank())) return service.listarTodos();
        return service.listar(nome, status);
    }
}
