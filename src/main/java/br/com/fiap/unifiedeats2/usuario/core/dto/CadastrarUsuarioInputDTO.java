package br.com.fiap.unifiedeats2.usuario.core.dto;

import java.util.List;

public record CadastrarUsuarioInputDTO(String nome, String email, String login, String senha, String cep,
                                    String logradouro, String numero, String complemento, String bairro, String cidade,
                                    String estado, List<Long> tiposUsuario) {
}
