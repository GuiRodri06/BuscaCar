package model.servicos;

import model.entities.contas.Cliente;           // Classe Cliente
import model.entities.contas.Administrador;     // Classe Administrador
import model.entities.contas.Usuario;           // Classe/interface base Usuario

import java.sql.*;                               // JDBC para banco de dados
import java.util.Scanner;                        // Para entrada do usuário no console

public class UsuarioDAO implements InterfaceUsuarioDAO {

    // Método privado que retorna uma conexão com o banco SQLite
    private Connection conectar() {
        String url = "jdbc:sqlite:db/sistema.db"; // URL do banco de dados SQLite
        try {
            return DriverManager.getConnection(url); // Tenta conectar
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage()); // Se falhar, mostra erro
            return null;
        }
    }

    // Verifica se o email já existe na tabela usuarios
    public boolean emailExiste(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?"; // Consulta simples para verificar existência
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // PreparedStatement para evitar SQL injection
            stmt.setString(1, email);                            // Seta o parâmetro do email
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();                                // Se encontrou pelo menos 1 resultado, retorna true
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;                                        // Em caso de erro, retorna false
        }
    }

    // Cria uma conta nova na tabela usuarios, com os dados passados
    public boolean criarConta(String nome, String email, String senha, String tipo, Integer nif, Integer telemovel) {
        if (emailExiste(email)) {                               // Primeiro verifica se o email já existe
            System.out.println("E-mail já cadastrado.");
            return false;                                       // Se existe, cancela criação
        }

        // Query para inserir dados na tabela usuarios
        String sql = "INSERT INTO usuarios(nome, email, senha, tipo, nif, telemovel) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);                            // Param 1: nome
            stmt.setString(2, email);                           // Param 2: email
            stmt.setString(3, senha);                           // Param 3: senha
            stmt.setString(4, tipo);                            // Param 4: tipo (cliente ou admin)
            stmt.setInt(5, nif);                                // Param 5: nif
            stmt.setInt(6, telemovel);                          // Param 6: telemovel
            stmt.executeUpdate();                               // Executa a inserção
            System.out.println("Usuário cadastrado com sucesso.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();                                // Se der erro, printa stack trace
            return false;
        }
    }

    // Verifica login do usuário pelo email e senha
    public boolean login(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?"; // Consulta para verificar login
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);                           // Seta email
            stmt.setString(2, senha);                           // Seta senha
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();                               // Retorna true se encontrou usuário com email e senha
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Busca um usuário pelo email e retorna um objeto Usuario (Cliente ou Administrador)
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?"; // Consulta por email
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);                           // Seta parâmetro email
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {                                    // Se encontrou registro
                String tipo = rs.getString("tipo");             // Pega o tipo (cliente/admin)
                String nome = rs.getString("nome");             // Nome
                String senha = rs.getString("senha");           // Senha
                int nif = rs.getInt("nif");                      // NIF
                int telemovel = rs.getInt("telemovel");          // Telemóvel

                if ("cliente".equalsIgnoreCase(tipo)) {         // Se for cliente, cria objeto Cliente
                    Cliente cliente = new Cliente(nome,  senha, email, nif, telemovel);
                    return cliente;
                } else if ("admin".equalsIgnoreCase(tipo)) {    // Se for admin, cria objeto Administrador
                    return new Administrador(nome, senha, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;                                            // Se não encontrou usuário, retorna null
    }

    // Lista todos os usuários do tipo cliente ordenados pelo nome
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
            while (rs.next()) {                                  // Para cada usuário cliente encontrado
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int nif = rs.getInt("nif");
                int telemovel = rs.getInt("telemovel");

                // Imprime os dados formatados no console
                System.out.printf("ID: %-3d | Nome: %-20s | Email: %-25s | NIF: %-9d | Telemóvel: %d%n",
                        id, nome, email, nif, telemovel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage()); // Se der erro, imprime mensagem
        }
    }

    // Deleta um usuário pelo ID, com confirmação e proteção para não deletar admins
    public void deletarUsuarioPorId(int id) {
        String verificaSql = "SELECT nome, tipo FROM usuarios WHERE id = ?"; // Consulta para verificar usuário
        String deletarSql = "DELETE FROM usuarios WHERE id = ?";             // Query para deletar usuário

        try (Connection conn = conectar()) {
            PreparedStatement stmtVerifica = conn.prepareStatement(verificaSql);
            stmtVerifica.setInt(1, id);                                     // Seta ID do usuário
            ResultSet rs = stmtVerifica.executeQuery();

            if (rs.next()) {                                                // Se usuário existe
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");

                if (tipo.equals("admin")) {                                 // Não permite deletar admin
                    System.out.println("Não é permitido deletar a conta do administrador.");
                    return;
                }

                System.out.printf("Usuário encontrado: %s (ID: %d)%n", nome, id);
                System.out.print("Deseja realmente deletar? (s/n): ");
                Scanner sc = new Scanner(System.in);
                String resp = sc.nextLine().trim().toLowerCase();

                if (resp.equals("s")) {                                     // Se confirmado pelo usuário
                    PreparedStatement stmtDel = conn.prepareStatement(deletarSql);
                    stmtDel.setInt(1, id);                                 // Seta ID para deletar
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
                System.out.println("Usuário com ID " + id + " não encontrado."); // Se usuário não existe
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

}
