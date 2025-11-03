package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public UsuarioOutDto criar(@RequestBody @Valid UsuarioInDto in) {
        return service.criar(in);
    }
    @GetMapping
    public List<UsuarioOutDto> listar() { return service.listarTodos(); }
}
