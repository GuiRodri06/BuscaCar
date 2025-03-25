package model.entities.contas;

public class Administrador extends Usuario{

    public Administrador() {
    }

    public Administrador(String name, String usuario, String senha, String email) {
        super(name, usuario, senha, email);
    }

    // metodo para verificar se a paessoa é um administrador
    public static void verificarAdmin() {

        if (nomeUsuario.trim().equals("adm") && email.trim().equals("adm@gmail.com") && senha.trim().equals("adm123")) {

        }
    }

}