package br.com.fiap.unifiedeats2.restaurante.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import lombok.Getter;

@Getter
public class Restaurante {
    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Usuario usuario;
}
