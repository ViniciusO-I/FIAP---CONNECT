package br.com.fiapconnect.challenge.mappers;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.entities.Grupo;

public class GrupoMapper {
    public static Grupo toEntity(GrupoInDto in) {
        Grupo g = new Grupo();
        g.setNomeDoProjeto(in.getNomeDoProjeto());
        g.setStatus(in.getStatus());
        return g;
    }
    public static GrupoOutDto toOutDto(Grupo g) {
        return new GrupoOutDto(g.getId(), g.getNomeDoProjeto(), g.getStatus());
    }
}
