package model.servicos; // Define o pacote onde essa classe está inserida

import model.entities.contas.Usuario; // Importa a classe Usuario, que representa o usuário do sistema
import java.util.Scanner; // Importa a classe Scanner, usada para capturar entrada do usuário via console

public class AutenticacaoLogin {

    public static Usuario userLogado; // Variável estática que representa o usuário atualmente logado

    public static void login() { // Método responsável por realizar o login (ou criar conta, caso não exista)
        Scanner scanner = new Scanner(System.in); // Scanner para capturar entradas do console
        UsuarioDAO dao = new UsuarioDAO(); // Instancia o DAO responsável por acessar os dados dos usuários

        System.out.println("========== LOGIN =========="); // Título da interface de login no console

        // Solicita email do usuário
        System.out.print("Email: ");
        String email = scanner.nextLine().trim(); // Captura e limpa espaços em branco

        // Solicita senha do usuário
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim(); // Captura e limpa espaços em branco

        // Tenta realizar o login com os dados informados
        if (dao.login(email, senha)) {
            // Se o login for bem-sucedido, busca o usuário no banco de dados pelo email
            userLogado = dao.buscarPorEmail(email);

            // Verifica se encontrou o usuário
            if (userLogado != null) {
                System.out.printf("Login bem-sucedido! Seja bem-vindo, %s ",
                        userLogado.getName()); // Mensagem de boas-vindas com o nome do usuário
            } else {
                System.out.println("Erro ao buscar usuário logado."); // Caso algo dê errado ao buscar o usuário
            }
        } else {
            // Caso o login falhe, pergunta se o usuário deseja criar uma conta
            System.out.println("Usuário não encontrado. Deseja criar uma conta? (s/n)");
            String resp = scanner.nextLine().trim(); // Captura a resposta

            if (resp.equalsIgnoreCase("s")) { // Se o usuário quiser criar a conta

                // Coleta os dados necessários para criar o novo usuário
                System.out.print("Nome completo: ");
                String nome = scanner.nextLine();

                System.out.print("Insira seu NIF: ");
                int nif = Integer.parseInt(scanner.nextLine()); // Captura NIF (assumindo que é um número inteiro)

                System.out.print("Insira seu número de telemóvel: ");
                int telemovel = Integer.parseInt(scanner.nextLine()); // Captura número de telefone

                // Tenta criar a conta com os dados informados
                if (dao.criarConta(nome, email, senha, "cliente", nif, telemovel)) {
                    System.out.println("Conta criada com sucesso!"); // Mensagem de sucesso
                } else {
                    System.out.println("Erro ao criar conta."); // Mensagem de erro ao tentar criar conta
                }
            } else {
                // Se o usuário não quiser criar uma conta, encerra o programa
                System.out.println("Encerrando o sistema.");
                System.exit(0);
            }
        }
    }
}

