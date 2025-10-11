package br.com.fiapconnect.challenge.service;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;

import java.util.List;
public interface UsuarioService {
    UsuarioOutDto criar(UsuarioInDto usuarioEntrada);
    List<UsuarioOutDto> listar(String filtroTurma, String filtroNome);
    UsuarioOutDto buscarPorId(Long identificadorUsuario);
    void deletar(Long identificadorUsuario);
}
