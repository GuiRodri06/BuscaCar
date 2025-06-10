package model.servicos;

import java.sql.*;
import java.util.Scanner;
import model.entities.carros.carrosCondi;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements InterfaceCarroDAO{

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

    public void cadastrarCarro(int idModelo, int ano, double preco, int km, String combustivel) {
        String sql = "INSERT INTO carro(id_modelo, ano, preco, km, combustivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idModelo);
            stmt.setInt(2, ano);
            stmt.setDouble(3, preco);
            stmt.setInt(4, km);
            stmt.setString(5, combustivel);
            stmt.executeUpdate();
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarTodosCarros() {
        String sql = """
        SELECT 
            c.id,
            m.nome AS marca, 
            mo.nome AS modelo, 
            c.ano, 
            c.preco,
            c.km,
            c.combustivel
        FROM carro c
        JOIN modelos mo ON c.id_modelo = mo.id
        JOIN marcas m ON mo.id_marca = m.id
        ORDER BY m.nome, mo.nome, c.ano;
    """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=========== CARROS DISPONÍVEIS ===========");
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double preco = rs.getDouble("preco");
                int km = rs.getInt("km");
                String combustivel = rs.getString("combustivel");
                System.out.printf("ID: %-3d | Marca: %-10s | Modelo: %-12s | Ano: %d | Preço: %.2f € | Km: %d | Combustível: %s%n",
                        id, marca, modelo, ano, preco, km, combustivel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }
    }

    public void deletarCarroPorId(int idCarro) {
        String sqlVerifica = "SELECT c.id, m.nome AS marca, mo.nome AS modelo, c.ano FROM carro c " +
                "JOIN modelos mo ON c.id_modelo = mo.id " +
                "JOIN marcas m ON mo.id_marca = m.id " +
                "WHERE c.id = ?";

        String sqlDelete = "DELETE FROM carro WHERE id = ?";

        try (Connection conn = conectar()) {
            PreparedStatement verifica = conn.prepareStatement(sqlVerifica);
            verifica.setInt(1, idCarro);
            ResultSet rs = verifica.executeQuery();

            if (rs.next()) {
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");

                System.out.printf("Carro encontrado: %s %s (%d)%n", marca, modelo, ano);
                System.out.print("Deseja realmente deletar? (s/n): ");

                Scanner sc = new Scanner(System.in);
                String opcao = sc.nextLine().trim().toLowerCase();

                if (opcao.equals("s")) {
                    PreparedStatement delete = conn.prepareStatement(sqlDelete);
                    delete.setInt(1, idCarro);
                    int linhas = delete.executeUpdate();

                    if (linhas > 0) {
                        System.out.println("Carro deletado com sucesso.");
                    } else {
                        System.out.println("Erro ao deletar o carro.");
                    }
                } else {
                    System.out.println("Operação cancelada.");
                }
            } else {
                System.out.println("Carro com ID " + idCarro + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar carro: " + e.getMessage());
        }
    }

    public List<carrosCondi> listarCarrosComoObjetos() {
        List<carrosCondi> lista = new ArrayList<>();

        String sql = """
        SELECT 
            c.id,
            m.nome AS marca, 
            mo.nome AS modelo, 
            c.ano, 
            c.preco,
            c.km,
            c.combustivel
        FROM carro c
        JOIN modelos mo ON c.id_modelo = mo.id
        JOIN marcas m ON mo.id_marca = m.id
        ORDER BY m.nome, mo.nome, c.ano;
    """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String marca      = rs.getString("marca");
                String modelo     = rs.getString("modelo");
                int ano           = rs.getInt("ano");
                int preco         = (int) rs.getDouble("preco");  // pode arredondar ou converter para int
                int km            = rs.getInt("km");
                String combustivel = rs.getString("combustivel");


                carrosCondi carro = new carrosCondi(
                        id, marca, modelo, km, ano, combustivel, preco);

                // aplica as regras de negócio
                carro.calcularPrecoKm100000(km);
                carro.calcularPrecoAno2015(ano);
                carro.calcularPrecoEletrico(combustivel);
                carro.calcularPrecoHybrido(combustivel);
                carro.calcularPrecoPorMarcaCara(marca);
                carro.calcularPreçoMarcasBaixas(marca);
                carro.calcularPrecoDiesel(combustivel);
                

                lista.add(carro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter carros: " + e.getMessage());
        }

        return lista;
    }

    public void imprimirCarrosCondicionados() {
        List<carrosCondi> lista = listarCarrosComoObjetos();

        System.out.println("======= CARROS COM TARIFA CALCULADA =======");

        for (carrosCondi c : lista) {
            System.out.printf("ID: %-3d | Modelo: %-10s | Submodelo: %-12s | Ano: %d | Km: %d | Combustível: %-9s | Tarifa €/dia: %.2f%n",
                    c.getId(), c.getModelo(), c.getSubmodelo(), c.getAno(), c.getKm(), c.getConbustivel(), c.getTarifaDiaBase());
        }
    }


}