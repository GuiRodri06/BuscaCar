package model.entities.servicos;

import model.entities.contas.Administrador;
import model.entities.contas.Usuario;

public class MenuService {

    // Metodo para mostrar o menu da empresa
    public static void mostrarMenu() {

        System.out.println("Acesse qual área você quer:");

        // verificar se foi instanciado um admnistrador, se nao, é um cliente
        if (AutenticacaoLogin.userLogado instanceof Administrador) {
            mostrarMenuAdmin();

        } else {
            mostrarMenuCliente();
        }
    }

    // Metodo para mostrar o menu sendo um Administrador
    public static void mostrarMenuAdmin() {

        System.out.println("[1] - Lista de carros"); // adcionar a lista de carros
        System.out.println("[2] - Acessar a conta");
        System.out.println("[3] - Ver relatórios finaceiros");
    }

    // Metodo para mostrar o menu sendo um Cliente
    public static void mostrarMenuCliente() {

        System.out.println("[1] - Lista de carros"); // adcionar a lista de carros
        System.out.println("[2] - Acessar a conta");
        System.out.println("[3] - Acessar o carrinho");
        System.out.println("[4] - Finalizar o aluguel");
    }
}