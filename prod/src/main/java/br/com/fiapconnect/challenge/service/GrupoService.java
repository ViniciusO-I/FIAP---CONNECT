package br.com.fiapconnect.challenge.service;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;

import java.util.List;

public interface GrupoService {
    GrupoOutDto criar(GrupoInDto grupoEntrada);
    List<GrupoOutDto> listar(String filtroStatus, String filtroNome);
    GrupoOutDto buscarPorId(Long identificadorGrupo);
    void deletar(Long identificadorGrupo);


}
