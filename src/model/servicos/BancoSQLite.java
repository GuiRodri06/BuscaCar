package model.servicos; // Define o pacote da aplicação onde esta classe está localizada

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Importações necessárias para manipular banco de dados com JDBC

public class BancoSQLite {

    // Constante com o caminho do banco de dados SQLite
    private static final String URL = "jdbc:sqlite:db/sistema.db";

    // Método para conectar ao banco de dados
    public static Connection conectar() {
        try {
            // Retorna uma conexão ativa com o banco de dados
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            // Se ocorrer erro na conexão, imprime a mensagem de erro
            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }

    // Método responsável por criar as tabelas do banco, caso ainda não existam
    public static void criarTabelas() {

        // Comando SQL para criar a tabela de usuários
        String sqlUsuarios = """
        CREATE TABLE IF NOT EXISTS usuarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,         -- ID único gerado automaticamente
            nome TEXT NOT NULL,                           -- Nome completo do usuário
            email TEXT UNIQUE NOT NULL,                   -- Email único (chave alternativa)
            senha TEXT NOT NULL,                          -- Senha do usuário
            usuario TEXT UNIQUE NOT NULL,                 -- Nome de usuário (único, embora não utilizado no login atual)
            nif INTEGER NOT NULL,                         -- Número de identificação fiscal
            telemovel INTEGER NOT NULL,                   -- Número de telemóvel (telefone)
            tipo TEXT NOT NULL CHECK (tipo IN ('admin', 'cliente')) -- Define se é admin ou cliente
        );
        """;

        // Comando SQL para criar a tabela de marcas de carro
        String sqlMarcas = """
        CREATE TABLE IF NOT EXISTS marcas (
            id INTEGER PRIMARY KEY AUTOINCREMENT,         -- ID único da marca
            nome TEXT UNIQUE NOT NULL                     -- Nome da marca, não pode repetir
        );
        """;

        // Comando SQL para criar a tabela de modelos de carro
        String sqlModelos = """
        CREATE TABLE IF NOT EXISTS modelos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,         -- ID único do modelo
            nome TEXT NOT NULL,                           -- Nome do modelo do carro
            id_marca INTEGER NOT NULL,                    -- Referência à marca (chave estrangeira)
            FOREIGN KEY (id_marca) REFERENCES marcas(id)  -- Relação entre modelo e marca
        );
        """;

        // Comando SQL para criar a tabela de carros disponíveis
        String sqlCarro = """
        CREATE TABLE IF NOT EXISTS carro (
            id INTEGER PRIMARY KEY AUTOINCREMENT,         -- ID único do carro
            id_modelo INTEGER NOT NULL,                   -- Referência ao modelo do carro
            ano INTEGER NOT NULL,                         -- Ano de fabricação
            preco REAL NOT NULL,                          -- Preço do carro
            km INTEGER NOT NULL,                          -- Quilometragem
            combustivel TEXT NOT NULL,                    -- Tipo de combustível
            FOREIGN KEY (id_modelo) REFERENCES modelos(id) -- Relação entre carro e modelo
        );
        """;

        // Bloco try-with-resources garante que a conexão e o statement sejam fechados corretamente
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            // Executa os comandos SQL de criação de tabelas
            stmt.execute(sqlUsuarios);  // Cria a tabela usuarios
            stmt.execute(sqlMarcas);    // Cria a tabela marcas
            stmt.execute(sqlModelos);   // Cria a tabela modelos
            stmt.execute(sqlCarro);     // Cria a tabela carro

        } catch (SQLException e) {
            // Exibe mensagem de erro, caso ocorra algum problema ao criar as tabelas
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}