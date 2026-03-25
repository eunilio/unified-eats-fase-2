package br.com.fiap.unifiedeats2.usuario.core.dto;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;

import java.util.List;

public record UsuarioOutputDTO(
        Long id,
        String nome,
        String email,
        String login,
        EnderecoOutputDTO endereco,
        List<String> tipoUsuarios
) {
}