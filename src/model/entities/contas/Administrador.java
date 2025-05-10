package model.entities.contas;

import model.servicos.AcoesAdministrador;

public class Administrador extends Usuario implements AcoesAdministrador {

    public Administrador() {}

    public Administrador(String name, String senha, String email) {
        super(name, senha, email);
    }

    @Override
    public String acessarConta() {
        return String.format("E-mail = %s \nSenha = %s",
                 getEmail(), getSenha());
    }

    @Override
    public void visualizarRelatorios() {
        // TODO: implementar visualização de relatórios
    }
}
