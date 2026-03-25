package br.com.fiap.unifiedeats2.usuario.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime ultimaAtualizacao;
    private Endereco endereco;
    private List<TipoUsuario> tipoUsuarios;

    public Usuario(String nome, String email, String login, String senha, Endereco endereco, List<TipoUsuario> tipoUsuarios) {
        verificaNome(nome);
        verificaEmail(email);
        verificaLogin(login);
        verificaSenha(senha);
        verificaEndereco(endereco);
        verificaTipoUsuarios(tipoUsuarios);

        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Usuario(Long id, String nome, String email, String login, String senha,
                   Endereco endereco, List<TipoUsuario> tipoUsuarios) {
        this.id = id;
        verificaNome(nome);
        verificaEmail(email);
        verificaLogin(login);
        verificaSenha(senha);
        verificaEndereco(endereco);
        verificaTipoUsuarios(tipoUsuarios);
    }

    public Long id() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }

    public String email() {
        return this.email;
    }

    public String login() {
        return this.login;
    }

    public String senha() {
        return this.senha;
    }

    public LocalDateTime ultimaAtualizacao() {
        return this.ultimaAtualizacao;
    }

    public Endereco endereco() {
        return this.endereco;
    }

    public List<TipoUsuario> tipoUsuarios() {
        return List.copyOf(this.tipoUsuarios);
    }

    public boolean  possuiEmail(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    public boolean possuiLogin(String login) {
        return this.login.equalsIgnoreCase(login);
    }

    public boolean senhaIgualA(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }

    public void atualizarCadastro(String nome, String email, String login, Endereco endereco) {
        verificaNome(nome);
        verificaEmail(email);
        verificaLogin(login);
        verificaEndereco(endereco);

        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void alterarSenha(String senha) {
        verificaSenha(senha);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void adicionaTipoUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo Usuário inválido");
        }

        List<TipoUsuario> novaLista = new ArrayList<>(this.tipoUsuarios);
        novaLista.add(tipoUsuario);

        verificaTipoUsuarios(novaLista);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void removerTipoUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo Usuário inválido");
        }

        List<TipoUsuario> novaLista = new ArrayList<>(this.tipoUsuarios);
        novaLista.remove(tipoUsuario);

        verificaTipoUsuarios(novaLista);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    private void verificaNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome.trim();
    }

    private void verificaEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }

        String emailFormatado = email.trim();

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!emailFormatado.matches(regex)) {
            throw new IllegalArgumentException("Email inválido.");
        }

        this.email = emailFormatado;
    }

    private void verificaLogin(String login) {
        if (login == null || login.trim().length() < 3) {
            throw new IllegalArgumentException("Login inválido");
        }
        this.login = login.trim();
    }

    private void verificaSenha(String senha) {
        if (senha == null || senha.length() < 5) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.senha = senha;
    }

    private void verificaEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço inválido");
        }
        this.endereco = endereco;
    }

    private void verificaTipoUsuarios(List<TipoUsuario> tipoUsuarios) {

        if (tipoUsuarios == null || tipoUsuarios.isEmpty()) {
            throw new IllegalArgumentException("Tipo Usuário inválido");
        }

        Set<TipoUsuario> tiposUnicos = new HashSet<>();

        for (TipoUsuario tipo : tipoUsuarios) {

            if (tipo == null) {
                throw new IllegalArgumentException("Tipo Usuário inválido");
            }

            if (!tiposUnicos.add(tipo)) {
                throw new IllegalArgumentException("Tipo Usuário duplicado: " + tipo);
            }
        }

        this.tipoUsuarios = new ArrayList<>(tipoUsuarios);
    }

    private void verificaTipoUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo Usuário inválido");
        }
    }
}