package model.entities.contas;

public class Administrador extends Usuario{


    public Administrador(String name, String usuario, String senha, String email) {
        super(name, usuario, senha, email);
    }

    /* Metodo para verificar se o usuário e a senha estão corretos */
    @Override
    public boolean autenticar() {
        return false;
    }
}
