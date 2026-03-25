package br.com.fiap.unifiedeats2.compartilhado.core.dto;

public record EnderecoOutputDTO(String cep, String logradouro, String numero, String complemento, String bairro,
                                String cidade, String estado) {
}
