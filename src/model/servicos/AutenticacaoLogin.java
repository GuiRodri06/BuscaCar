package model.servicos;

import model.entities.contas.Usuario;

import java.util.Scanner;

public class AutenticacaoLogin {

    public static Usuario userLogado;

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("========== LOGIN ==========");

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        if (dao.login(email, senha)) {
            userLogado = dao.buscarPorEmail(email);
            if (userLogado != null) {
                System.out.printf("Login bem-sucedido! Seja bem-vindo, %s ",
                        userLogado.getName());
            } else {
                System.out.println("Erro ao buscar usuário logado.");
            }
        } else {
            System.out.println("Usuário não encontrado. Deseja criar uma conta? (s/n)");
            String resp = scanner.nextLine().trim();

            if (resp.equalsIgnoreCase("s")) {

                System.out.print("Nome completo: ");
                String nome = scanner.nextLine();

                System.out.print("Insira seu NIF: ");
                int nif = Integer.parseInt(scanner.nextLine());

                System.out.print("Insira seu número de telemóvel: ");
                int telemovel = Integer.parseInt(scanner.nextLine());

                if (dao.criarConta(nome, email, senha, "cliente", nif, telemovel)) {
                    System.out.println("Conta criada com sucesso!");
                } else {
                    System.out.println("Erro ao criar conta.");
                }
            } else {
                System.out.println("Encerrando o sistema.");
                System.exit(0);
            }
        }
    }
}
