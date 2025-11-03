package br.com.fiapconnect.challenge.service.impl;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.entities.Grupo;
import br.com.fiapconnect.challenge.mappers.GrupoMapper;
import br.com.fiapconnect.challenge.repositories.GrupoRepository;
import br.com.fiapconnect.challenge.service.GrupoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class GrupoServiceImpl implements GrupoService {
    private final GrupoRepository repo;
    public GrupoServiceImpl(GrupoRepository repo) { this.repo = repo; }
    public GrupoOutDto criar(GrupoInDto in) {
        Grupo g = repo.save(GrupoMapper.toEntity(in));
        return GrupoMapper.toOutDto(g);
    }
    public List<GrupoOutDto> listarTodos() {
        return repo.findAll().stream().map(GrupoMapper::toOutDto).toList();
    }
    public List<GrupoOutDto> listar(String nome, String status) {
        return repo.filtrar((nome==null||nome.isBlank())?null:nome,
                            (status==null||status.isBlank())?null:status)
                   .stream().map(GrupoMapper::toOutDto).toList();
    }
}
