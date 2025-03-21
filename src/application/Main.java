package application;

import model.entities.contas.Administrador;
import model.entities.contas.Cliente;
import model.entities.contas.Usuario;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner txt = new Scanner(System.in);

        // inicio de uma tela de login, provavelmente eu vou criar um metodo so para isso
        System.out.print("Fazer login agora? (s/n) ");
        char ch = txt.next().charAt(0);
        if (ch == 's') {
            login();
        }


    }

    // metodo para fazer login
    public static void login() {

        Scanner txt = new Scanner(System.in);

        System.out.print("Insira seu nome: ");
        String nome = txt.nextLine();

        System.out.print("Insira seu nome de usuario: ");
        String nomeUsuario = txt.nextLine();

        System.out.print("Insira seu email: ");
        String email = txt.nextLine();

        System.out.print("Insira sua senha: ");
        String senha = txt.nextLine();

        if (nomeUsuario.trim().equals("adm") && email.trim().equals("adm@gmail.com") && senha.trim().equals("adm123")) {
            System.out.println("Seja bem-vindo adm!");
            Usuario usuario = new Administrador(nome, nomeUsuario, senha, email);


        } else {
            System.out.println("Seja bem-vindo ao BuscaCar! Para completar preencha com as utimas informacoes");

            System.out.print("Insira seu nif: ");
            int nif = txt.nextInt();

            System.out.print("Insira seu numero de telemóvel: ");
            int telemovel = txt.nextInt();

            Usuario usuario = new Cliente(nome, nomeUsuario, senha, email, nif, telemovel);

        }

    }
}