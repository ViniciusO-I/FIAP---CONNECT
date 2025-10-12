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

    private final UsuarioRepository repo;
    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }
    @Transactional
    public UsuarioOutDto criar(UsuarioInDto in) {
        validar(in);
        repo.findByRm(in.rm).ifPresent(u -> { throw new IllegalArgumentException("rm ja cadastrado"); });

        Usuario usuario = new Usuario();
        usuario.setRm(in.rm);
        usuario.setNome(in.nome);
        usuario.setTurma(in.turma);
        usuario = repo.save(usuario);

        UsuarioOutDto saidaUsuario = new UsuarioOutDto();
        saidaUsuario.id = usuario.getId();
        saidaUsuario.rm = usuario.getRm();
        saidaUsuario.nome = usuario.getNome();
        saidaUsuario.turma = usuario.getTurma();
        return saidaUsuario;
    }

    @Transactional(readOnly = true)
    public List<UsuarioOutDto> listar(String turma, String nome) {
        List<Usuario> lista;
        if (!vazio(turma)) lista = repo.findByTurma(turma);
        else if (!vazio(nome)) lista = repo.findByNomeContainingIgnoreCase(nome);
        else lista = repo.findAll();

        return lista.stream().map(usuario -> {
            UsuarioOutDto saidaUsuario = new UsuarioOutDto();
            saidaUsuario.id = usuario.getId();
            saidaUsuario.rm = usuario.getRm();
            saidaUsuario.nome = usuario.getNome();
            saidaUsuario.turma = usuario.getTurma();
            return saidaUsuario;
        }).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioOutDto buscarPorId(Long id) {
        Usuario usuario = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("usuario nao encontrado"));
        UsuarioOutDto saidaUsuario = new UsuarioOutDto();
        saidaUsuario.id = usuario.getId();
        saidaUsuario.rm = usuario.getRm();
        saidaUsuario.nome = usuario.getNome();
        saidaUsuario.turma = usuario.getTurma();
        return saidaUsuario;
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("usuario nao encontrado");
        repo.deleteById(id);
    }

    private void validar(UsuarioInDto in) {
        if (in == null) throw new IllegalArgumentException("entrada obrigatoria");
        if (vazio(in.rm)) throw new IllegalArgumentException("rm obrigatorio");
        if (vazio(in.nome)) throw new IllegalArgumentException("nome obrigatorio");
    }

    private static boolean vazio(String vazio) {
        return vazio == null || vazio.isBlank();
    }
}