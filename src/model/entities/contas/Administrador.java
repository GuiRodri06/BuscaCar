package model.entities.contas;

public class Administrador extends Usuario {

    public Administrador() {}

    public Administrador(String name, String senha, String email) {
        super(name, senha, email);
    }

    @Override
    public String acessarConta() {
        return String.format("E-mail = %s \nSenha = %s",
                 getEmail(), getSenha());
    }

}
