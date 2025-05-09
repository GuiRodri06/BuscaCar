package model.servicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoSQLite {

    private static final String URL = "jdbc:sqlite:db/sistema.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }

    public static void criarTabelas() {
        String sqlUsuarios = """
        CREATE TABLE IF NOT EXISTS usuarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            email TEXT UNIQUE NOT NULL,
            senha TEXT NOT NULL,
            usuario TEXT UNIQUE NOT NULL,
            nif INTEGER NOT NULL,
            telemovel INTEGER NOT NULL,
            tipo TEXT NOT NULL CHECK (tipo IN ('admin', 'cliente'))
        );
        """;

        String sqlMarcas = """
        CREATE TABLE IF NOT EXISTS marcas (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT UNIQUE NOT NULL
        );
        """;

        String sqlModelos = """
        CREATE TABLE IF NOT EXISTS modelos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            id_marca INTEGER NOT NULL,
            FOREIGN KEY (id_marca) REFERENCES marcas(id)
        );
        """;

        String sqlCarro = """
        CREATE TABLE IF NOT EXISTS carro (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            id_modelo INTEGER NOT NULL,
            ano INTEGER NOT NULL,
            preco REAL NOT NULL,
            FOREIGN KEY (id_modelo) REFERENCES modelos(id)
        );
        """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlUsuarios);
            stmt.execute(sqlMarcas);
            stmt.execute(sqlModelos);
            stmt.execute(sqlCarro);

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}

