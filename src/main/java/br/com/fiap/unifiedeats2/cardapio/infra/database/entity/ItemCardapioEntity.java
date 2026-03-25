package br.com.fiap.unifiedeats2.cardapio.infra.database.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_cardapio")
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "disponivel_apenas_no_local", nullable = false)
    private Boolean disponivelApenasNoLocal;

    @Column(nullable = false)
    private String foto;

    @Column(name = "restaurante_id", nullable = false)
    private Long restauranteId;

    public ItemCardapioEntity() {
    }

    public ItemCardapioEntity(
            Long id,
            String nome,
            String descricao,
            BigDecimal preco,
            Boolean disponivelApenasNoLocal,
            String foto,
            Long restauranteId
    ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivelApenasNoLocal = disponivelApenasNoLocal;
        this.foto = foto;
        this.restauranteId = restauranteId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getDisponivelApenasNoLocal() {
        return disponivelApenasNoLocal;
    }

    public String getFoto() {
        return foto;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }
}