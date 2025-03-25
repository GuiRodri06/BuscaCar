package model.entities.contas;

import model.entities.servicos.AcoesUsuario;

import java.util.Scanner;

public abstract class Usuario implements AcoesUsuario {

    private String name;
    private String usuario;
    private String senha;
    private String email;

    public Usuario() {
    }

    public Usuario(String name, String usuario, String senha, String email) {
        this.name = name;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public void acessarConta() {

    }

}