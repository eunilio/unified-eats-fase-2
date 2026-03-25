package br.com.fiap.unifiedeats2.usuario.core.dto;

public record AlterarSenhaUsuarioInputDTO(
        Long id,
        String senhaAtual,
        String novaSenha
) {
}