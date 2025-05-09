package model.entities.contas;

import model.servicos.AcoesAdministrador;

public class Administrador extends Usuario implements AcoesAdministrador {

    public Administrador() {}

    public Administrador(String name, String usuario, String senha, String email) {
        super(name, usuario, senha, email);
    }

    @Override
    public String acessarConta() {
        return String.format("Usuario = %s \nE-mail = %s \nSenha = %s",
                getUsuario(), getEmail(), getSenha());
    }

    @Override
    public void visualizarRelatorios() {
        // TODO: implementar visualização de relatórios
    }
}
