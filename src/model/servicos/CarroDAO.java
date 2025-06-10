package model.servicos;

// Importa classes necessárias para conexão com banco de dados e manipulação de listas
import java.sql.*;
import java.util.Scanner;
import model.entities.carros.carrosCondi; // Importa a classe que representa o carro com lógica de tarifação
import java.util.ArrayList;
import java.util.List;

// Classe responsável por realizar operações de persistência relacionadas a carros
public class CarroDAO implements InterfaceCarroDAO {

    // Método privado para obter uma conexão com o banco de dados SQLite
    private Connection conectar() {
        // Caminho do banco de dados local (arquivo .db)
        String url = "jdbc:sqlite:db/sistema.db";
        try {
            // Retorna uma conexão válida com o banco
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            // Em caso de erro, imprime a mensagem e retorna null
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }

    // Cadastra uma nova marca no banco de dados
    public int cadastrarMarca(String nomeMarca) {
        // SQL que insere a marca apenas se ela ainda não existir (IGNORE)
        String sqlInsert = "INSERT OR IGNORE INTO marcas(nome) VALUES (?)";
        // SQL para obter o ID da marca (mesmo que já exista)
        String sqlSelect = "SELECT id FROM marcas WHERE nome = ?";
        try (Connection conn = conectar()) {
            // Prepara a inserção
            PreparedStatement insert = conn.prepareStatement(sqlInsert);
            insert.setString(1, nomeMarca);
            insert.executeUpdate();

            // Depois tenta buscar o ID da marca cadastrada
            PreparedStatement select = conn.prepareStatement(sqlSelect);
            select.setString(1, nomeMarca);
            ResultSet rs = select.executeQuery();
            if (rs.next()) return rs.getInt("id"); // Se encontrou, retorna o ID
        } catch (SQLException e) {
            e.printStackTrace(); // Mostra detalhes do erro
        }
        return -1; // Marca não foi cadastrada ou erro ocorreu
    }

    // Cadastra um novo modelo associado a uma marca
    public int cadastrarModelo(String nomeModelo, int idMarca) {
        String sqlInsert = "INSERT OR IGNORE INTO modelos(nome, id_marca) VALUES (?, ?)";
        String sqlSelect = "SELECT id FROM modelos WHERE nome = ? AND id_marca = ?";
        try (Connection conn = conectar()) {
            // Insere o modelo se não existir
            PreparedStatement insert = conn.prepareStatement(sqlInsert);
            insert.setString(1, nomeModelo);
            insert.setInt(2, idMarca);
            insert.executeUpdate();

            // Seleciona o ID do modelo correspondente
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

    // Cadastra um carro completo no banco de dados
    public void cadastrarCarro(int idModelo, int ano, double preco, int km, String combustivel) {
        String sql = "INSERT INTO carro(id_modelo, ano, preco, km, combustivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Define os parâmetros na ordem do SQL
            stmt.setInt(1, idModelo);
            stmt.setInt(2, ano);
            stmt.setDouble(3, preco);
            stmt.setInt(4, km);
            stmt.setString(5, combustivel);
            stmt.executeUpdate(); // Executa o INSERT
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace(); // Mostra erro se ocorrer
        }
    }

    // Lista todos os carros cadastrados no sistema com detalhes
    public void listarTodosCarros() {
        // Consulta SQL com junção entre carro, modelo e marca
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
            // Percorre todos os resultados retornados
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                double preco = rs.getDouble("preco");
                int km = rs.getInt("km");
                String combustivel = rs.getString("combustivel");

                // Imprime os dados formatados
                System.out.printf("ID: %-3d | Marca: %-10s | Modelo: %-12s | Ano: %d | Preço: %.2f € | Km: %d | Combustível: %s%n",
                        id, marca, modelo, ano, preco, km, combustivel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }
    }

    // Deleta um carro específico após confirmação do usuário
    public void deletarCarroPorId(int idCarro) {
        // Primeiro busca as informações do carro pelo ID
        String sqlVerifica = """
            SELECT c.id, m.nome AS marca, mo.nome AS modelo, c.ano
            FROM carro c 
            JOIN modelos mo ON c.id_modelo = mo.id 
            JOIN marcas m ON mo.id_marca = m.id 
            WHERE c.id = ?
        """;

        // SQL que remove o carro do banco
        String sqlDelete = "DELETE FROM carro WHERE id = ?";

        try (Connection conn = conectar()) {
            // Verifica se o carro existe
            PreparedStatement verifica = conn.prepareStatement(sqlVerifica);
            verifica.setInt(1, idCarro);
            ResultSet rs = verifica.executeQuery();

            if (rs.next()) {
                // Mostra detalhes do carro
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");

                System.out.printf("Carro encontrado: %s %s (%d)%n", marca, modelo, ano);
                System.out.print("Deseja realmente deletar? (s/n): ");

                Scanner sc = new Scanner(System.in); // Entrada do usuário
                String opcao = sc.nextLine().trim().toLowerCase();

                if (opcao.equals("s")) {
                    // Executa a exclusão
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

    // Retorna uma lista de carros com regras de negócio aplicadas (tarifa)
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
                // Extrai os dados do carro
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                int preco = (int) rs.getDouble("preco"); // arredonda o preço para facilitar
                int km = rs.getInt("km");
                String combustivel = rs.getString("combustivel");

                // Cria um objeto carrosCondi com os dados básicos
                carrosCondi carro = new carrosCondi(
                        id, marca, modelo, km, ano, combustivel, preco);

                // Aplica as regras de tarifação do sistema
                carro.calcularPrecoKm100000(km);
                carro.calcularPrecoAno2015(ano);
                carro.calcularPrecoEletrico(combustivel);
                carro.calcularPrecoHybrido(combustivel);
                carro.calcularPrecoPorMarcaCara(marca);
                carro.calcularPreçoMarcasBaixas(marca);
                carro.calcularPrecoDiesel(combustivel);

                // Adiciona o carro à lista
                lista.add(carro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter carros: " + e.getMessage());
        }

        return lista; // Retorna todos os carros como objetos prontos para exibir
    }

    // Imprime na tela a lista de carros com tarifa calculada
    public void imprimirCarrosCondicionados() {
        List<carrosCondi> lista = listarCarrosComoObjetos();

        System.out.println("======= CARROS COM TARIFA CALCULADA =======");

        // Exibe os detalhes de cada carro, incluindo a tarifa por dia
        for (carrosCondi c : lista) {
            System.out.printf("ID: %-3d | Modelo: %-10s | Submodelo: %-12s | Ano: %d | Km: %d | Combustível: %-9s | Tarifa €/dia: %.2f%n",
                    c.getId(), c.getModelo(), c.getSubmodelo(), c.getAno(), c.getKm(), c.getConbustivel(), c.getTarifaDiaBase());
        }
    }
}