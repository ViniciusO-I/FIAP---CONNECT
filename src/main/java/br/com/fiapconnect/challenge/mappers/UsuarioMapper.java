package br.com.fiapconnect.challenge.mappers;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.entities.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioInDto in) {
        Usuario u = new Usuario();
        u.setRm(in.getRm());
        u.setNome(in.getNome());
        u.setTurma(in.getTurma());
        return u;
    }
    public static UsuarioOutDto toOutDto(Usuario u) {
        return new UsuarioOutDto(u.getId(), u.getRm(), u.getNome(), u.getTurma());
    }
}
