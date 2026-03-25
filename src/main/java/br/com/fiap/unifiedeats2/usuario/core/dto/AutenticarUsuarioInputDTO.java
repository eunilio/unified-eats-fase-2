package br.com.fiap.unifiedeats2.usuario.core.dto;

public record AutenticarUsuarioInputDTO(
        String login,
        String senha
) {
}