package application;

import model.servicos.AutenticacaoLogin;
import model.servicos.HomePage;
import model.servicos.MenuService;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner txt = new Scanner(System.in);

        HomePage.Home(); // chama o metodo da pagina inical

        AutenticacaoLogin.login(); // chama o metodo de login, se clicar em s ou sim, implementar um butao

        MenuService.mostrarMenu(); // chama o metodo menu

    }

}
