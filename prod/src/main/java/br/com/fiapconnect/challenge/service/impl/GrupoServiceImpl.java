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

    private final GrupoRepository grupoRepository;
    public GrupoServiceImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;


    }
    @Transactional
    public GrupoOutDto criar(GrupoInDto grupoEntrada) {
        if (grupoEntrada == null || campoVazio(grupoEntrada.getNomeDoProjeto()) || campoVazio(grupoEntrada.getStatus())) {
            throw new IllegalArgumentException("nome do projeto - Transactional criar");
        }

        String statusNormalizado = grupoEntrada.getStatus().toUpperCase();
        if (!statusNormalizado.equals("BUSCANDO") && !statusNormalizado.equals("COMPLETO")) {
            throw new IllegalArgumentException("status - grupo de entrada - Transactional criar");
        }

        Grupo novoGrupo = new Grupo();
        novoGrupo.setNomeDoProjeto(grupoEntrada.getNomeDoProjeto());
        novoGrupo.setStatus(statusNormalizado);

        novoGrupo = grupoRepository.save(novoGrupo);

        GrupoOutDto grupoSaida = new GrupoOutDto();
        grupoSaida.setId(novoGrupo.getId());
        grupoSaida.setNomeDoProjeto(novoGrupo.getNomeDoProjeto());
        grupoSaida.setStatus(novoGrupo.getStatus());
        return grupoSaida;
    }
    @Transactional(readOnly = true)
    public List<GrupoOutDto> listar(String filtroStatus, String filtroNome) {
        List<Grupo> listaGrupos;
        if (!campoVazio(filtroStatus)) {
            listaGrupos = grupoRepository.findByStatusIgnoreCase(filtroStatus);
        } else if (!campoVazio(filtroNome)) {
            listaGrupos = grupoRepository.findByNomeDoProjetoContainingIgnoreCase(filtroNome);
        } else {
            listaGrupos = grupoRepository.findAll();
        }




        return listaGrupos.stream().map(grupo -> {
            GrupoOutDto grupoSaida = new GrupoOutDto();
            grupoSaida.setId(grupo.getId());
            grupoSaida.setNomeDoProjeto(grupo.getNomeDoProjeto());
            grupoSaida.setStatus(grupo.getStatus());
            return grupoSaida;
        }).toList();
    }
    @Transactional(readOnly = true)
    public GrupoOutDto buscarPorId(Long identificadorGrupo) {
        Grupo grupoEncontrado = grupoRepository.findById(identificadorGrupo)
                .orElseThrow(() -> new IllegalArgumentException("grupo nao encontrado"));

        GrupoOutDto grupoSaida = new GrupoOutDto();
        grupoSaida.setId(grupoEncontrado.getId());
        grupoSaida.setNomeDoProjeto(grupoEncontrado.getNomeDoProjeto());
        grupoSaida.setStatus(grupoEncontrado.getStatus());
        return grupoSaida;
    }
    @Transactional
    public void deletar(Long identificadorGrupo) {
        if (!grupoRepository.existsById(identificadorGrupo)) {
            throw new IllegalArgumentException("grupo nao encontrado");
        }

        grupoRepository.deleteById(identificadorGrupo);
    }
    private static boolean campoVazio(String textoCampo) {
        return textoCampo == null || textoCampo.isBlank();
    }
}
