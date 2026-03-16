package br.com.fiap.unifiedeats2.usuario.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TipoUsuario {

    private String nome;

    public TipoUsuario(String nome) {
        verificaNome(nome);
    }

    public void atualizarNome(String nome) {
        verificaNome(nome);
    }

    private void verificaNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome inválido");
        }

        this.nome = nome.trim().toUpperCase();
    }
}