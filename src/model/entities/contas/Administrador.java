package model.entities.contas;

import model.entities.servicos.AcoesAdministrador;

public class Administrador extends Usuario implements AcoesAdministrador {

    public Administrador() {
    }

    public Administrador(String name, String usuario, String senha, String email) {
        super(name, usuario, senha, email);
    }

    @Override
    public void visualizarRelatorios() {

    }

    @Override
    public void acessarConta() {

    }
}