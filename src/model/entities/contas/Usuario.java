package model.entities.contas;

public abstract class Usuario {

    protected String name;
    protected String usuario;
    protected String senha;
    protected String email;

    public Usuario() {
    }

    public Usuario(String name, String usuario, String senha, String email) {
        this.name = name;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

}