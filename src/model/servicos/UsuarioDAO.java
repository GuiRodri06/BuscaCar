package model.servicos;

import model.entities.contas.Cliente;
import model.entities.contas.Administrador;
import model.entities.contas.Usuario;

import java.sql.*;

public class UsuarioDAO {

    private Connection conectar() {
        String url = "jdbc:sqlite:db/sistema.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    public boolean emailExiste(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean criarConta(String nome, String email, String senha, String tipo, Integer nif, Integer telemovel) {
        if (emailExiste(email)) {
            System.out.println("E-mail já cadastrado.");
            return false;
        }

        String sql = "INSERT INTO usuarios(nome, email, senha, tipo, nif, telemovel) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, tipo);
            stmt.setInt(5, nif);
            stmt.setInt(6, telemovel);
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    Usuario user;
                    if ("admin".equalsIgnoreCase(tipo)) {
                        user = new Administrador();
                    } else {
                        user = new Cliente();
                    }
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setSenha(rs.getString("senha"));
                    user.setTipo(tipo);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listarUsuarios() {
        String sql = "SELECT id, nome, email, tipo FROM usuarios";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("---- Lista de Usuários ----");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Email: %s | Tipo: %s%n",
                        rs.getInt("id"), rs.getString("nome"),
                        rs.getString("email"), rs.getString("tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário excluído com sucesso.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

