package model.entities.contas;

import java.util.Date;

public abstract class Usuario {

    private String name;
    private String usuario;
    private String senha;
    private String email;

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

    /* Metodo para verificar se o usuário e a senha estão corretos */
    public abstract boolean autenticar();

}