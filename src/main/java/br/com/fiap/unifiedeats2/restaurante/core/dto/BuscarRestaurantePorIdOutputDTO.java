package br.com.fiap.unifiedeats2.restaurante.core.dto;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;

public record BuscarRestaurantePorIdOutputDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {
}