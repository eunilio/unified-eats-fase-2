package br.com.fiap.unifiedeats2.tipousuario.infra.database.entity;

import br.com.fiap.unifiedeats2.usuario.infra.database.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao;

    @ManyToMany(mappedBy = "tipoUsuarios")
    private List<UsuarioEntity> usuarios;
}