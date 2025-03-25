package application;

import model.entities.contas.Administrador;
import model.entities.contas.Usuario;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner txt = new Scanner(System.in);

        System.out.print("Fazer login agora? (s/n) ");
        char ch = txt.next().charAt(0);

        if (ch == 's') {
            Usuario.login(); // chama o metodo de login, se clicar em s ou sim, implementar um butao
        }

        menu(); // chama o metodo menu

    }

    public static void menu() {
        // Menu da empresa
        System.out.println("Acesse qual área você quer:");
        // verificar o menu da empresa
        if (new Administrador().getUsuario() == "adm") {
            System.out.println("[1] - Lista de carros"); // adcionar a lista de carros
            System.out.println("[2] - Acessar a conta");
            System.out.println("[3] - Acessar o carrinho");
        }
    }

}
