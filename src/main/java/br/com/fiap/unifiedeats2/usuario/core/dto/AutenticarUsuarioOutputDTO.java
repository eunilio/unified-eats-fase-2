package br.com.fiap.unifiedeats2.usuario.core.dto;

public record AutenticarUsuarioOutputDTO(
        Long id,
        String nome,
        String email,
        String login
) {
}