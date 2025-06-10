package model.servicos;

import model.entities.contas.Usuario;

public interface InterfaceUsuarioDAO {

    boolean emailExiste(String email);
    boolean criarConta(String nome, String email, String senha, String tipo, Integer nif, Integer telemovel);
    boolean login(String email, String senha);
    Usuario buscarPorEmail(String email);
    void listarUsuariosClientes();
    void deletarUsuarioPorId(int id);

}
