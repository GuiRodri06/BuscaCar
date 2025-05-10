package model.servicos;

import model.entities.contas.Cliente;
import model.entities.contas.Administrador;
import model.entities.contas.Usuario;

import java.sql.*;
import java.util.Scanner;

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
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                int nif = rs.getInt("nif");
                int telemovel = rs.getInt("telemovel");

                if ("cliente".equalsIgnoreCase(tipo)) {
                    Cliente cliente = new Cliente(nome,  senha, email, nif, telemovel);
                    return cliente;
                } else if ("admin".equalsIgnoreCase(tipo)) {
                    return new Administrador(nome, senha, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listarUsuariosClientes() {
        String sql = """
        SELECT id, nome, email, nif, telemovel
        FROM usuarios
        WHERE tipo = 'cliente'
        ORDER BY nome;
    """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("========== USUÁRIOS CLIENTES ==========");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int nif = rs.getInt("nif");
                int telemovel = rs.getInt("telemovel");

                System.out.printf("ID: %-3d | Nome: %-20s | Email: %-25s | NIF: %-9d | Telemóvel: %d%n",
                        id, nome, email, nif, telemovel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }


    public void deletarUsuarioPorId(int id) {
        String verificaSql = "SELECT nome, tipo FROM usuarios WHERE id = ?";
        String deletarSql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = conectar()) {
            PreparedStatement stmtVerifica = conn.prepareStatement(verificaSql);
            stmtVerifica.setInt(1, id);
            ResultSet rs = stmtVerifica.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");

                if (tipo.equals("admin")) {
                    System.out.println("Não é permitido deletar a conta do administrador.");
                    return;
                }

                System.out.printf("Usuário encontrado: %s (ID: %d)%n", nome, id);
                System.out.print("Deseja realmente deletar? (s/n): ");
                Scanner sc = new Scanner(System.in);
                String resp = sc.nextLine().trim().toLowerCase();

                if (resp.equals("s")) {
                    PreparedStatement stmtDel = conn.prepareStatement(deletarSql);
                    stmtDel.setInt(1, id);
                    int linhas = stmtDel.executeUpdate();

                    if (linhas > 0) {
                        System.out.println("Usuário deletado com sucesso.");
                    } else {
                        System.out.println("Erro ao deletar o usuário.");
                    }
                } else {
                    System.out.println("Operação cancelada.");
                }
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

}

