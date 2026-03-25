package br.com.fiap.unifiedeats2.tipousuario.core.domain;

import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioInvalidoException;

import java.time.LocalDateTime;

public class TipoUsuario {

    private Long id;
    private String nome;
    private LocalDateTime ultimaAtualizacao;

    public TipoUsuario(Long id, String nome) {
        this.id = id;
        definirNome(nome);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public TipoUsuario(Long id, String nome, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public void atualizarCadastro(String nome) {
        definirNome(nome);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    private void definirNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new TipoUsuarioInvalidoException("Nome do tipo de usuário é obrigatório.");
        }

        this.nome = nome.trim();
    }

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public LocalDateTime ultimaAtualizacao() {
        return ultimaAtualizacao;
    }
}