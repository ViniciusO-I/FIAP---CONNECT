package br.com.fiapconnect.challenge.service.impl;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.entities.Grupo;
import br.com.fiapconnect.challenge.entities.Usuario;
import br.com.fiapconnect.challenge.repositories.GrupoRepository;
import br.com.fiapconnect.challenge.repositories.UsuarioRepository;
import br.com.fiapconnect.challenge.service.GrupoService;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioServiceImpl(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public UsuarioOutDto criar(UsuarioInDto in) {
        if (in == null || vazio(in.rm) || vazio(in.nome)) {
            throw new IllegalArgumentException("rm obrigatorioo");
        }
        repositorio.findByRm(in.rm).ifPresent(usuario -> {
            throw new IllegalArgumentException("o cadastro ja existe");
        });

        Usuario usuario = new Usuario();
        usuario.setRm(in.rm);
        usuario.setNome(in.nome);
        usuario.setTurma(in.turma);
        usuario = repositorio.save(usuario);

        UsuarioOutDto out = new UsuarioOutDto();
        out.id = usuario.getId();
        out.rm = usuario.getRm();
        out.nome = usuario.getNome();
        out.turma = usuario.getTurma();
        return out;
    }

    @Transactional(readOnly = true)
    public List<UsuarioOutDto> listar(String turma, String nome) {
        List<Usuario> lista;
        if (!vazio(turma)) {
            lista = repositorio.findByTurma(turma);
        } else if (!vazio(nome)) {
            lista = repositorio.findByNomeContainingIgnoreCase(nome);
        } else {
            lista = repositorio.findAll();
        }
        return lista.stream().map(usuario -> {
            UsuarioOutDto out = new UsuarioOutDto();
            out.id = usuario.getId();
            out.rm = usuario.getRm();
            out.nome = usuario.getNome();
            out.turma = usuario.getTurma();
            return out;
        }).toList();
    }

    @Override
    public UsuarioOutDto buscarId(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    public UsuarioOutDto buscarPorId(Long id) {
        Usuario novoUsuario = repositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("usuario nao existe"));
        UsuarioOutDto out = new UsuarioOutDto();
        out.id = novoUsuario.getId();
        out.rm = novoUsuario.getRm();
        out.nome = novoUsuario.getNome();
        out.turma = novoUsuario.getTurma();
        return out;
    }

    @Transactional
    public void deletar(Long id) {
        if (!repositorio.existsById(id)) throw new IllegalArgumentException("usuario nao existe");
        repositorio.deleteById(id);
    }

    private static boolean vazio(String resultado) {

        return resultado == null || resultado.isBlank();
    }
}
