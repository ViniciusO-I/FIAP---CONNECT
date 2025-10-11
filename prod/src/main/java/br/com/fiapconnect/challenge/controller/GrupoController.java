package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.GrupoInDto;
import br.com.fiapconnect.challenge.dto.GrupoOutDto;
import br.com.fiapconnect.challenge.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public Object processarRequisicao(@RequestBody RequisicaoGrupo requisicao) {

        System.out.println("requisicao recebida controller");
        System.out.println("requsicao enviada: " + requisicao.getAcao());

        try {
            switch (requisicao.getAcao()) {

                case "criar":
                    System.out.println("cria novo grupo");
                    GrupoOutDto novoGrupo = grupoService.criar(requisicao.getGrupo());
                    System.out.println("criado id: " + novoGrupo.id);
                    return novoGrupo;

                case "listar":
                    System.out.println("listar grupo");
                    List<GrupoOutDto> lista = grupoService.listar(requisicao.getStatus(), requisicao.getNomeDoProjeto());
                    System.out.println("total de grupo encontrado " + lista.size());
                    return lista;

                case "buscar":
                    System.out.println("buscar grup" + requisicao.getId());
                    return grupoService.buscarPorId(requisicao.getId());

                case "deletar":
                    System.out.println("deletear grupo" + requisicao.getId());
                    grupoService.deletar(requisicao.getId());
                    System.out.println("remover grupo " + requisicao.getId());
                    return "removido";

                default:
                    System.out.println("err" + requisicao.getAcao());
                    return "err-1" + requisicao.getAcao();
            }

        } catch (Exception e) {
            System.out.println("falha " + e.getMessage());
            return "falha-1" + e.getMessage();
        }
    }
    public static class RequisicaoGrupo {
        private String acao;
        private Long id;
        private String status;
        private String nomeDoProjeto;
        private GrupoInDto grupo;

        public String getAcao() {
            return acao;
        }
        public void setAcao(String acao) {
            this.acao = acao;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getNomeDoProjeto() {
            return nomeDoProjeto;
        }
        public void setNomeDoProjeto(String nomeDoProjeto) {
            this.nomeDoProjeto = nomeDoProjeto;
        }
        public GrupoInDto getGrupo() {
            return grupo;
        }
        public void setGrupo(GrupoInDto grupo) {
            this.grupo = grupo;
        }
    }
}
