package br.com.fiap.unifiedeats2.usuario.infra.database.entity;

import br.com.fiap.unifiedeats2.compartilhado.infra.database.entity.EnderecoEntity;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao;

    @Embedded
    private EnderecoEntity endereco;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_tipo_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_usuario_id")
    )
    private List<TipoUsuarioEntity> tipoUsuarios = new ArrayList<>();

    public UsuarioEntity(String nome, String email, String login, String senha, LocalDateTime ultimaAtualizacao, EnderecoEntity endereco, List<TipoUsuarioEntity> tipoUsuarios) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.endereco = endereco;
        this.tipoUsuarios = tipoUsuarios;
    }
}