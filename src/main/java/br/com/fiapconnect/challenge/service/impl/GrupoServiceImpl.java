package br.com.fiapconnect.challenge.service.impl;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.entities.Grupo;
import br.com.fiapconnect.challenge.repositories.GrupoRepository;
import br.com.fiapconnect.challenge.service.GrupoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {
    private final GrupoRepository repo;
    public GrupoServiceImpl(GrupoRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public GrupoOutDto criar(GrupoInDto in) {
        validar(in);
        Grupo grupo = new Grupo();
        grupo.setNomeDoProjeto(in.nomeDoProjeto);
        grupo.setStatus(in.status);
        grupo = repo.save(grupo);

        GrupoOutDto saidaGrupo = new GrupoOutDto();
        saidaGrupo.id = grupo.getId();
        saidaGrupo.nomeDoProjeto = grupo.getNomeDoProjeto();
        saidaGrupo.status = grupo.getStatus();
        return saidaGrupo;
    }

    @Transactional(readOnly = true)
    public List<GrupoOutDto> listar(String status, String nome) {
        List<Grupo> lista;
        if (!vazio(status)) {
            lista = repo.findByStatusIgnoreCase(status);
        } else if (!vazio(nome)) {
            lista = repo.findByNomeDoProjetoContainingIgnoreCase(nome);
        } else {
            lista = repo.findAll();
        }
        return lista.stream().map(grupo -> {
            GrupoOutDto saidaGrupo = new GrupoOutDto();
            saidaGrupo.id = grupo.getId();
            saidaGrupo.nomeDoProjeto = grupo.getNomeDoProjeto();
            saidaGrupo.status = grupo.getStatus();
            return saidaGrupo;
        }).toList();
    }

    @Transactional(readOnly = true)
    public GrupoOutDto buscarPorId(Long id) {
        Grupo grupo = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("grupo nao encontrado"));
        GrupoOutDto saidaGrupo = new GrupoOutDto();
        saidaGrupo.id = grupo.getId();
        saidaGrupo.nomeDoProjeto = grupo.getNomeDoProjeto();
        saidaGrupo.status = grupo.getStatus();
        return saidaGrupo;
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("grupo nao encontrado");
        repo.deleteById(id);
    }

    private void validar(GrupoInDto in) {
        if (in == null) throw new IllegalArgumentException("entrada obrigatoria");
        if (vazio(in.nomeDoProjeto)) throw new IllegalArgumentException("nomeDoProjeto obrigatorio");
        if (vazio(in.status)) throw new IllegalArgumentException("status obrigatorio");
    }

    private static boolean vazio(String vazio) {
        return vazio == null || vazio.isBlank();
    }
}