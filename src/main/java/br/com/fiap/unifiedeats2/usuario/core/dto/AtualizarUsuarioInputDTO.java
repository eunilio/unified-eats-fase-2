package br.com.fiap.unifiedeats2.usuario.core.dto;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;

public record AtualizarUsuarioInputDTO(
        Long id,
        String nome,
        String email,
        String login,
        Endereco endereco
) {
}