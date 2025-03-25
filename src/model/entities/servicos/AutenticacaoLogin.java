package model.entities.servicos;

import model.entities.contas.Administrador;
import model.entities.contas.Cliente;
import model.entities.contas.Usuario;

import java.util.Scanner;

public class AutenticacaoLogin {

    // Campo estático para armazenar o usuário logado
    public static Usuario userLogado;

    /* Método para fazer login */
    public static void login() {
        Scanner txt = new Scanner(System.in);

        System.out.print("Insira seu nome de usuário: ");
        String nomeUsuario = txt.nextLine().trim(); // .trim serve para eliminar os espacos desnecessarios

        System.out.print("Insira seu email: ");
        String email = txt.nextLine().trim();

        System.out.print("Insira sua senha: ");
        String senha = txt.nextLine().trim();

        // Autenticação do administrador (usuário fixo)
        if (nomeUsuario.equals("adm") && email.equals("adm@gmail.com") && senha.equals("adm123")) {
            System.out.println("Seja bem-vindo, Administrador!");

            userLogado = new Administrador("Administrador", nomeUsuario, senha, email);
        }
        // Autenticação do cliente
        else {
            System.out.println("Seja bem-vindo ao BuscaCar! Complete seu cadastro:");

            System.out.print("Insira seu nome completo: ");
            String nome = txt.nextLine();

            System.out.print("Insira seu NIF: ");
            int nif = Integer.parseInt(txt.nextLine()); // Melhor que nextInt() para evitar bugs

            System.out.print("Insira seu número de telemóvel: ");
            int telemovel = Integer.parseInt(txt.nextLine());

            userLogado = new Cliente(nome, nomeUsuario, senha, email, nif, telemovel);
        }
    }
}