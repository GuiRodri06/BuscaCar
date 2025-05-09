package model.servicos;

import java.sql.*;

public class CarroDAO {

    private Connection conectar() {
        String url = "jdbc:sqlite:db/sistema.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    public int cadastrarMarca(String nomeMarca) {
        String sqlInsert = "INSERT OR IGNORE INTO marcas(nome) VALUES (?)";
        String sqlSelect = "SELECT id FROM marcas WHERE nome = ?";
        try (Connection conn = conectar()) {
            PreparedStatement insert = conn.prepareStatement(sqlInsert);
            insert.setString(1, nomeMarca);
            insert.executeUpdate();

            PreparedStatement select = conn.prepareStatement(sqlSelect);
            select.setString(1, nomeMarca);
            ResultSet rs = select.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int cadastrarModelo(String nomeModelo, int idMarca) {
        String sqlInsert = "INSERT OR IGNORE INTO modelos(nome, id_marca) VALUES (?, ?)";
        String sqlSelect = "SELECT id FROM modelos WHERE nome = ? AND id_marca = ?";
        try (Connection conn = conectar()) {
            PreparedStatement insert = conn.prepareStatement(sqlInsert);
            insert.setString(1, nomeModelo);
            insert.setInt(2, idMarca);
            insert.executeUpdate();

            PreparedStatement select = conn.prepareStatement(sqlSelect);
            select.setString(1, nomeModelo);
            select.setInt(2, idMarca);
            ResultSet rs = select.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void cadastrarCarro(int idModelo, int ano, double preco) {
        String sql = "INSERT INTO carro(id_modelo, ano, preco) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idModelo);
            stmt.setInt(2, ano);
            stmt.setDouble(3, preco);
            stmt.executeUpdate();
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
