package model.servicos;

import model.entities.contas.Cliente;

import java.util.Scanner;

public class MenuAlterar {

    static Scanner txt = new Scanner(System.in);

    public static void MenuAlterarDados() {

        Cliente cliente = (Cliente) AutenticacaoLogin.userLogado;
        int i = 0;

        while (i != 5) {
            System.out.println();
            System.out.println("==== MENU DE ALTERACAO ====");
            System.out.println("[1] - Alterar nome de usuário");
            System.out.println("[2] - Alterar a senha");
            System.out.println("[3] - Alterar e-mail");
            System.out.println("[4] - Alterar nome");
            System.out.println("[5] - Sair");

            System.out.print("Escolha o que você vai querer alterar: ");
            i = txt.nextInt();
            txt.nextLine(); // limpar buffer

            System.out.println("==============================");

            switch (i) {
                case 1 -> alterarUsuario(cliente);
                case 2 -> alterarSenha(cliente);
                case 3 -> alterarEmail(cliente);
                case 4 -> alterarNome(cliente);
                case 5 -> System.out.println("Saindo do menu de alteração...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // ================= MÉTODOS DE ALTERAÇÃO =================

    private static void alterarUsuario(Cliente cliente) {
        System.out.println("\n=== Alteração de Usuário ===");
        System.out.print("Digite o novo nome de usuário: ");
        String novoUsuario = txt.nextLine().trim();
        cliente.alterarUsuario(novoUsuario);
    }

    private static void alterarSenha(Cliente cliente) {
        System.out.println("\n=== Alteração de Senha ===");
        System.out.print("Digite a senha atual: ");
        String atual = txt.nextLine().trim();
        System.out.print("Digite a nova senha: ");
        String nova = txt.nextLine().trim();
        cliente.alterarSenha(atual, nova);
    }

    private static void alterarEmail(Cliente cliente) {
        System.out.println("\n=== Alteração de E-mail ===");
        System.out.print("Digite o novo e-mail: ");
        String novoEmail = txt.nextLine().trim();
        cliente.alterarEmail(novoEmail);
    }

    private static void alterarNome(Cliente cliente) {
        System.out.println("\n=== Alteração de Nome ===");
        System.out.print("Digite o novo nome: ");
        String novoNome = txt.nextLine().trim();
        cliente.alterarNome(novoNome);
    }
}
