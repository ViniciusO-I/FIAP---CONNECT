package br.com.fiapconnect.challenge.service.impl;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.entities.Usuario;
import br.com.fiapconnect.challenge.repositories.UsuarioRepository;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // criar um novo usuario
    @Transactional
    public UsuarioOutDto criar(UsuarioInDto usuarioEntrada) {
        if (usuarioEntrada == null || campoVazio(usuarioEntrada.getRm()) || campoVazio(usuarioEntrada.getNome())) {
            throw new IllegalArgumentException("rm nome obrigatorio");
        }

        usuarioRepository.findByRm(usuarioEntrada.getRm()).ifPresent(usuarioExistente -> {
            throw new IllegalArgumentException("rm ja cadastrado");
        });

        Usuario novoUsuario = new Usuario();
        novoUsuario.setRm(usuarioEntrada.getRm());
        novoUsuario.setNome(usuarioEntrada.getNome());
        novoUsuario.setTurma(usuarioEntrada.getTurma());

        novoUsuario = usuarioRepository.save(novoUsuario);

        UsuarioOutDto usuarioSaida = new UsuarioOutDto();
        usuarioSaida.setId(novoUsuario.getId());
        usuarioSaida.setRm(novoUsuario.getRm());
        usuarioSaida.setNome(novoUsuario.getNome());
        usuarioSaida.setTurma(novoUsuario.getTurma());
        return usuarioSaida;
    }

    // listar
    @Transactional(readOnly = true)
    public List<UsuarioOutDto> listar(String filtroTurma, String filtroNome) {
        List<Usuario> listaUsuarios;

        if (!campoVazio(filtroTurma)) {
            listaUsuarios = usuarioRepository.findByTurma(filtroTurma);
        } else if (!campoVazio(filtroNome)) {
            listaUsuarios = usuarioRepository.findByNomeContainingIgnoreCase(filtroNome);
        } else {
            listaUsuarios = usuarioRepository.findAll();
        }

        return listaUsuarios.stream().map(usuario -> {
            UsuarioOutDto usuarioSaida = new UsuarioOutDto();
            usuarioSaida.setId(usuario.getId());
            usuarioSaida.setRm(usuario.getRm());
            usuarioSaida.setNome(usuario.getNome());
            usuarioSaida.setTurma(usuario.getTurma());
            return usuarioSaida;
        }).toList();
    }


    // retorna id

    @Transactional(readOnly = true)
    public UsuarioOutDto buscarPorId(Long identificadorUsuario) {
        var usuarioEncontrado = usuarioRepository.findById(identificadorUsuario)
                .orElseThrow(() -> new IllegalArgumentException("usuario nao encontrado"));

        var usuarioSaida = new UsuarioOutDto();
        usuarioSaida.setId(usuarioEncontrado.getId());
        usuarioSaida.setRm(usuarioEncontrado.getRm());
        usuarioSaida.setNome(usuarioEncontrado.getNome());
        usuarioSaida.setTurma(usuarioEncontrado.getTurma());
        return usuarioSaida;
    }



    @Transactional
    public void deletar(Long identificadorUsuario) {
        if (!usuarioRepository.existsById(identificadorUsuario)) {
            throw new IllegalArgumentException(" vazio-deletar");
        }
        usuarioRepository.deleteById(identificadorUsuario);
    }


    private static boolean campoVazio(String textoCampo) {
        return textoCampo == null || textoCampo.isBlank();
    }
}
