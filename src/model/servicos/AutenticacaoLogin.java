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
                System.out.printf("Login bem-sucedido! Seja bem-vindo, %s [%s]%n",
                        userLogado.getName(), userLogado.getTipo());
            } else {
                System.out.println("Erro ao buscar usuário logado.");
            }
        } else {
            System.out.println("Usuário não encontrado. Deseja criar uma conta? (s/n)");
            String resp = scanner.nextLine().trim();

            if (resp.equalsIgnoreCase("s")) {

                System.out.print("Nome completo: ");
                String nome = scanner.nextLine();

                System.out.println("Número de NIF:");
                int nif = scanner.nextInt();

                System.out.println("Número de Telemóvel:");
                int telemovel = scanner.nextInt();

                if (dao.criarConta(nome, email, senha, "admin", nif, telemovel)) {
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
