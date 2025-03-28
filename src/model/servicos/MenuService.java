package model.servicos;

import model.entities.contas.Administrador;
import model.entities.contas.Cliente;

import java.util.Scanner;

public class MenuService {

    static Scanner txt = new Scanner(System.in);

    // Metodo para mostrar o menu da empresa
    public static void mostrarMenu() {

        // verificar se foi instanciado um admnistrador, se nao, é um cliente
        if (AutenticacaoLogin.userLogado instanceof Administrador) {
            mostrarMenuAdmin();

        } else {
            mostrarMenuCliente();
        }
    }

    // Metodo para mostrar o menu sendo um Administrador
    public static void mostrarMenuAdmin() {

        Administrador administrador = (Administrador) AutenticacaoLogin.userLogado; // casting para acessar a autenticacao do adm e o objeto que foi instanciado

        while (true) {
            System.out.println();
            System.out.println("===== MENU ADMINISTRADOR =====");
            System.out.println();
            System.out.println("[1] - Lista de carros"); // adcionar a lista de carros
            System.out.println("[2] - Acessar a conta");
            System.out.println("[3] - Ver relatórios finaceiros");
            System.out.println("[4] - Sair");

            System.out.println();
            System.out.print("Qual área voce vai querer: ");
            int i = txt.nextInt();
            System.out.println();
            System.out.println("==============================");

            switch (i) {
                case 1:
                    System.out.println("Tem que implementar a lista de carros");
                    break;
                case 2:
                    System.out.println("Informacões sobre a conta do administrador:");
                    System.out.println();
                    System.out.println(administrador.acessarConta());
                    break;
                case 3:
                    System.out.println("Tem que implementar um relatorio financeiro");
                    break;
                case 4:
                    System.out.println("Saindo da aplicação... Até logo!");
                    System.exit(0); // Encerra a aplicação
                    break;
            }
            System.out.println("==============================");
        }
    }

    // Metodo para mostrar o menu sendo um Cliente
    public static void mostrarMenuCliente() {

        Cliente cliente = (Cliente) AutenticacaoLogin.userLogado; // casting para acessar a autenticacao do user e o objeto que foi instanciado

        while (true) {
            System.out.println("======= MENU =======");
            System.out.println();
            System.out.println("[1] - Lista de carros"); // adcionar a lista de carros
            System.out.println("[2] - Acessar a conta");
            System.out.println("[3] - Acessar o carrinho");
            System.out.println("[4] - Finalizar o aluguel");
            System.out.println("[5] - Sair");

            System.out.println("Qual área voce vai querer: ");
            int i = txt.nextInt();
            System.out.println();
            System.out.println("==============================");
            switch (i) {
                case 1:
                    System.out.println("Tem que implementar a lista de carros");
                    break;
                case 2:
                    System.out.println("Informacões sobre a conta:");
                    System.out.println();
                    System.out.println(cliente.acessarConta());

                    System.out.println();
                    System.out.println("Vai querer fazer alguma alteracao de dados pessoais (s/n): ");
                    char ch = txt.next().charAt(0);
                    if (ch == 's') {
                        menuAlteracaoDeInfo();
                    }

                    break;
                case 3:
                    System.out.println("Tem que implementar o carrinho");
                    break;
                case 4:
                    System.out.println("Tem que implementar a finalizacao");
                    break;
                case 5:
                    System.out.println("Saindo da aplicação... Até logo!");
                    System.exit(0); // Encerra a aplicação
                    break;
            }
            System.out.println("==============================");
        }
    }

    // implementar um menu de alteracao
    public static void menuAlteracaoDeInfo() {

    }

}