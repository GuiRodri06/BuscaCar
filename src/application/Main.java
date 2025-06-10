package application;

import model.servicos.AutenticacaoLogin;
import model.servicos.BancoSQLite;
import model.servicos.HomePage;
import model.servicos.MenuService;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        BancoSQLite.criarTabelas();

        HomePage.Home(); // chama o metodo da pagina inical

        AutenticacaoLogin.login(); // chama o metodo de login, se clicar em s ou sim, implementar um butao

        MenuService.mostrarMenu(); // chama o metodo menu

    }

}

