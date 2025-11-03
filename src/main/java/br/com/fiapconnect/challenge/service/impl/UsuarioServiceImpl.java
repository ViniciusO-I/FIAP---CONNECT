package br.com.fiapconnect.challenge.service.impl;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.entities.Usuario;
import br.com.fiapconnect.challenge.mappers.UsuarioMapper;
import br.com.fiapconnect.challenge.repositories.UsuarioRepository;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioServiceImpl(UsuarioRepository repo) { this.repo = repo; }
    public UsuarioOutDto criar(UsuarioInDto in) {
        if (repo.existsByRm(in.getRm())) throw new IllegalArgumentException("RM já cadastrado: " + in.getRm());
        Usuario u = repo.save(UsuarioMapper.toEntity(in));
        return UsuarioMapper.toOutDto(u);
    }
    public List<UsuarioOutDto> listarTodos() {
        return repo.findAll().stream().map(UsuarioMapper::toOutDto).toList();
    }
}
