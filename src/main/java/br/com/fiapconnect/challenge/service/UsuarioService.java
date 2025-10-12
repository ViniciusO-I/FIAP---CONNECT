package br.com.fiapconnect.challenge.service;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import java.util.List;

public interface UsuarioService {
    UsuarioOutDto criar(UsuarioInDto in);
    List<UsuarioOutDto> listar(String turma, String nome);
    UsuarioOutDto buscarPorId(Long id);
    void deletar(Long id);
}