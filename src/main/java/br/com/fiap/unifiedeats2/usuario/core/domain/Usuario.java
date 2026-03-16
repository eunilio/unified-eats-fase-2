package br.com.fiap.unifiedeats2.usuario.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {

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

    public void atualizarCadastro(String nome, String email, String login, Endereco endereco, List<TipoUsuario> tipoUsuarios) {
        verificaNome(nome);
        verificaEmail(email);
        verificaLogin(login);
        verificaEndereco(endereco);
        verificaTipoUsuarios(tipoUsuarios);

        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void alterarSenha(String senha) {
        verificaSenha(senha);
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void adicionaTipoUsuario(TipoUsuario tipoUsuario) {
        verificaTipoUsuario(tipoUsuario);

        List<TipoUsuario> novaLista = new ArrayList<>(this.tipoUsuarios);
        novaLista.add(tipoUsuario);

        verificaTipoUsuarios(novaLista);

        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void removerTipoUsuario(TipoUsuario tipoUsuario) {
        verificaTipoUsuario(tipoUsuario);

        if (!this.tipoUsuarios.contains(tipoUsuario)) {
            throw new IllegalArgumentException("Tipo Usuário não encontrado para o usuário");
        }

        if (this.tipoUsuarios.size() <= 1) {
            throw new IllegalArgumentException("Deve ter pelo menos um Tipo Usuário");
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
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        email = email.trim();

        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email.trim();
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