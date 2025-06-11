
//Guilherme Lucas
package application;

import model.servicos.AutenticacaoLogin;
import model.servicos.BancoSQLite;
import model.servicos.HomePage;
import model.servicos.MenuService;

import java.util.Locale;

/**
 * Classe de arranque da aplicação.
 * <p>
 * Fluxo em alto nível:
 * 1) Ajusta locale para US (garante ponto decimal em números, datas etc.)
 * 2) Garante que as tabelas SQLite existem (criação idempotente)
 * 3) Mostra a página inicial (banner / apresentação)
 * 4) Executa o processo de login/registro
 * 5) Direciona para o menu apropriado (Administrador ou Cliente)
 */
public class Main {

    public static void main(String[] args) {

        // 1. Padrão de formatação numérica
        Locale.setDefault(Locale.US);

        // 2. Criação (ou verificação) das tabelas necessárias no SQLite
        BancoSQLite.criarTabelas();

        // 3. Tela/bem‑vindo inicial (texto, logotipo, etc.)
        HomePage.Home();

        // 4. Processo de autenticação (entrada de credenciais)
        AutenticacaoLogin.login();

        // 5. Após login bem‑sucedido, mostra o menu correspondente ao tipo de utilizador
        MenuService.mostrarMenu();
    }
}


