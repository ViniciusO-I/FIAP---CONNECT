package br.com.fiapconnect.challenge.controller;

import br.com.fiapconnect.challenge.dto.UsuarioInDto;
import br.com.fiapconnect.challenge.dto.UsuarioOutDto;
import br.com.fiapconnect.challenge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Object processarRequisicao(@RequestBody RequisicaoUsuario requisicao) {
        System.out.println("controller acao" + requisicao.getAcao());

        switch (requisicao.getAcao()) {
            case "criar":
                return usuarioService.criar(requisicao.getUsuario());
            case "listar":
                return usuarioService.listar(requisicao.getTurma(), requisicao.getNome());
            case "buscar":
                return usuarioService.buscarPorId(requisicao.getId());
            case "deletar":
                usuarioService.deletar(requisicao.getId());
                return "remove usuario";
            default:
                return "acao nao encontrada " + requisicao.getAcao();
        }
    }
    public static class RequisicaoUsuario {
        private String acao;
        private Long id;
        private String turma;
        private String nome;
        private UsuarioInDto usuario;

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
        public String getTurma() {
            return turma;

        }
        public void setTurma(String turma) {
            
            this.turma = turma;
        }
        public String getNome() {
            
            return nome;
        }
        public void setNome(String nome)
        {
            this.nome = nome;
        }
        public UsuarioInDto getUsuario() {

            return usuario;
        }
        public void setUsuario(UsuarioInDto usuario) {
            this.usuario = usuario;



        }
    }
}
