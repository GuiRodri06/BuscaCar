//Guilherme Lucas

package model.servicos;


import model.entities.contas.Cliente;

import java.util.Scanner;

/**
 * Classe responsável por exibir um menu de alteração de dados pessoais
 * (senha, e‑mail, nome e número de telemóvel) para o cliente logado.
 * <p>
 * A lógica é toda em métodos estáticos porque o menu não mantém estado;
 * apenas manipula o objeto {@link Cliente} recuperado da sessão.
 */
public class MenuAlterar {

    /* Scanner compartilhado com todo o menu  ---------------------------- */
    static Scanner txt = new Scanner(System.in);

    /**
     * Método de entrada do menu. Exibe as opções até o utilizador escolher
     * «Sair». O objeto {@code AutenticacaoLogin.userLogado} deve já conter
     * um cliente autenticado antes de chamar este método.
     */
    public static void MenuAlterarDados() {

        // Cast seguro porque o menu só é mostrado a clientes
        Cliente cliente = (Cliente) AutenticacaoLogin.userLogado;
        int opcao = 0;

        // Loop principal: termina quando o utilizador escolhe 5 (Sair)
        while (opcao != 5) {
            System.out.println("\n==== MENU DE ALTERACAO ====");
            System.out.println("[1] - Alterar a senha");
            System.out.println("[2] - Alterar e-mail");
            System.out.println("[3] - Alterar nome");
            System.out.println("[4] - Alterar o número de telemóvel");
            System.out.println("[5] - Sair");

            // Leitura da opção
            System.out.print("\nEscolha o que você vai querer alterar: ");
            opcao = txt.nextInt();
            txt.nextLine();                 // limpa o "enter" pendente
            System.out.println("==============================");

            // Despacha para o método correspondente
            switch (opcao) {
                case 1 -> alterarSenha(cliente);
                case 2 -> alterarEmail(cliente);
                case 3 -> alterarNome(cliente);
                case 4 -> alterarNumero(cliente);
                case 5 -> System.out.println("Saindo do menu de alteração...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /* ================= MÉTODOS DE ALTERAÇÃO ================= */

    /**
     * Solicita a senha actual e a nova senha, delegando a validação ao método
     * {@code Cliente.alterarSenha()}.
     */
    private static void alterarSenha(Cliente cliente) {
        System.out.println("\n=== Alteração de Senha ===");
        System.out.print("Digite a senha atual: ");
        String atual = txt.nextLine().trim();  // trim() remove espaços extras
        System.out.print("Digite a nova senha: ");
        String nova = txt.nextLine().trim();
        cliente.alterarSenha(atual, nova);
    }

    /**
     * Lê um novo e‑mail e chama {@code Cliente.alterarEmail()}.
     */
    private static void alterarEmail(Cliente cliente) {
        System.out.println("\n=== Alteração de E-mail ===");
        System.out.print("Digite o novo e-mail: ");
        String novoEmail = txt.nextLine().trim();
        cliente.alterarEmail(novoEmail);
    }

    /**
     * Permite mudar o nome do cliente.
     */
    private static void alterarNome(Cliente cliente) {
        System.out.println("\n=== Alteração de Nome ===");
        System.out.print("Digite o novo nome: ");
        String novoNome = txt.nextLine().trim();
        cliente.alterarNome(novoNome);
    }

    /**
     * Valida que o input seja numérico antes de enviar para o método de
     * alteração. Usa {@code txt.hasNextInt()} para evitar {@link java.util.InputMismatchException}.
     */
    private static void alterarNumero(Cliente cliente) {
        System.out.println("\n=== Alteração de Número ===");
        System.out.print("Digite o novo número de telefone: ");

        // valida se o próximo token é realmente um inteiro
        while (!txt.hasNextInt()) {
            System.out.println("Número inválido! Digite apenas números.");
            txt.next(); // descarta input errado
        }
        int novoNumero = txt.nextInt();
        txt.nextLine(); // limpa o buffer
        cliente.alterarNumero(novoNumero);
    }
}
