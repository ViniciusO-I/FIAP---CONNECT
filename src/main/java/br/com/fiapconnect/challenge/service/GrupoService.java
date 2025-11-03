package br.com.fiapconnect.challenge.service;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import java.util.List;

public interface GrupoService {
    GrupoOutDto criar(GrupoInDto in);
    List<GrupoOutDto> listarTodos();
    List<GrupoOutDto> listar(String nome, String status);
}
