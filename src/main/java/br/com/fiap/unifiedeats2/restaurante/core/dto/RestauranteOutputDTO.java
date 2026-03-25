package br.com.fiap.unifiedeats2.restaurante.core.dto;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;

public record RestauranteOutputDTO(
        Long id,
        String nome,
        EnderecoOutputDTO endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {
}