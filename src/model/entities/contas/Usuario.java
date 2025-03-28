package model.entities.contas;

public abstract class Usuario {

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

    /* public void setUsuario(String usuario) {
        this.usuario = usuario;
    } */

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

    // metodo para alterar o usuario
    public void alterarUsuario(String novoUsuario) {
        if (novoUsuario != null && !novoUsuario.isEmpty()) {
            this.usuario = novoUsuario;
            System.out.println("Nome de usuario alterado com sucesso!");
        } else {
            System.out.println("Nome de usuario inválido!");
        }
    }

}