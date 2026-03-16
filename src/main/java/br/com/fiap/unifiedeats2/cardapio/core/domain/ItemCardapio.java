package br.com.fiap.unifiedeats2.cardapio.core.domain;

import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemCardapio {
    private Long id;
    private Restaurante restaurante;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean somenteNoLocal;
    private String fotoPath;
}
