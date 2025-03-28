package application;

import model.servicos.AutenticacaoLogin;
import model.servicos.MenuService;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner txt = new Scanner(System.in);

        System.out.print("Fazer login agora? (s/n) ");
        char ch = txt.next().charAt(0);

        if (ch == 's') {
            AutenticacaoLogin.login(); // chama o metodo de login, se clicar em s ou sim, implementar um butao
        }

        MenuService.mostrarMenu(); // chama o metodo menu

    }

}
