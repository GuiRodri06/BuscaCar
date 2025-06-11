//Guilherme Lucas

package model.servicos;

import model.entities.contas.Administrador;
import model.entities.contas.Cliente;

import java.util.Scanner;

/**
 * Classe responsável por exibir o menu principal da aplicação, 
 * direccionando o utilizador para opções de Administrador ou Cliente 
 * conforme o tipo de conta actualmente autenticado em {@link AutenticacaoLogin#userLogado}.
 */
public class MenuService {

    /* Scanner partilhado – evita criar múltiplas instâncias em cada ciclo */
    static Scanner txt = new Scanner(System.in);

    /**
     * Ponto de entrada: decide qual menu mostrar.
     *  - Se o utilizador logado é {@link Administrador}, abre menu de admin.
     *  - Caso contrário, assume {@link Cliente} e abre menu de cliente.
     */
    public static void mostrarMenu() {
        if (AutenticacaoLogin.userLogado instanceof Administrador) {
            mostrarMenuAdmin();
        } else {
            mostrarMenuCliente();
        }
    }

    /* ========================= MENU ADMIN ========================= */

    /**
     * Loop infinito que exibe as opções de administração até o utilizador
     * escolher «Sair» (opção 6), ocasião em que a aplicação termina.
     */
    public static void mostrarMenuAdmin() {

        // Obtém o objecto Administrador logado (pode não ser usado agora,
        // mas fica disponível p/ futuras validações de permissão ou dados).
        Administrador administrador = (Administrador) AutenticacaoLogin.userLogado;

        CarroDAO daocar  = new CarroDAO();  // operações sobre tabela de carros
        UsuarioDAO daouser = new UsuarioDAO(); // CRUD de utilizadores

        while (true) {
            System.out.println("\n===== MENU ADMINISTRADOR =====\n");
            System.out.println("[1] - Lista de carros");
            System.out.println("[2] - Lista de usuários cadastrados");
            System.out.println("[3] - Adicionar um novo carro");
            System.out.println("[4] - Deletar um carro");
            System.out.println("[5] - Deletar um usuário");
            System.out.println("[6] - Sair");

            System.out.print("\nQual área você vai querer: ");
            int opcao = txt.nextInt();
            txt.nextLine(); // limpa o ENTER
            System.out.println("==============================");

            switch (opcao) {
                case 1 -> daocar.listarTodosCarros();
                case 2 -> daouser.listarUsuariosClientes();
                case 3 -> CadastroCarroService.executarCadastro();
                case 4 -> {
                    System.out.print("Informe o ID do carro a ser deletado: ");
                    int idCar = Integer.parseInt(txt.nextLine());
                    daocar.deletarCarroPorId(idCar);
                }
                case 5 -> {
                    System.out.print("Digite o ID do usuário que deseja deletar: ");
                    int idUser = Integer.parseInt(txt.nextLine());
                    daouser.deletarUsuarioPorId(idUser);
                }
                case 6 -> {
                    System.out.println("Saindo da aplicação... Até logo!");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("==============================");
        }
    }

    /* ========================= MENU CLIENTE ========================= */

    /**
     * Loop principal para clientes: funções de visualização de carros,
     * gestão de conta e carrinho de aluguer.
     */
    public static void mostrarMenuCliente() {

        Cliente cliente   = (Cliente) AutenticacaoLogin.userLogado;
        CarroDAO daocar   = new CarroDAO();
        CarrinhoCliente carrinho = new CarrinhoCliente();

        while (true) {
            System.out.println("\n========== MENU ==========" );
            System.out.println("[1] - Lista de carros");
            System.out.println("[2] - Acessar a conta");
            System.out.println("[3] - Escolher um carro");
            System.out.println("[4] - Visualizar o carrinho");
            System.out.println("[5] - Finalizar o aluguel");
            System.out.println("[6] - Sair");

            System.out.print("\nQual área você vai querer: ");
            int opcao = txt.nextInt();
            txt.nextLine();
            System.out.println("==============================");

            switch (opcao) {
                case 1 -> daocar.imprimirCarrosCondicionados();
                case 2 -> {
                    System.out.println("Informações sobre a conta:\n" + cliente.acessarConta());
                    System.out.print("\nDeseja alterar dados pessoais (s/n): ");
                    char ch = txt.next().trim().toLowerCase().charAt(0);  
                    if (ch == 's') MenuAlterar.MenuAlterarDados();  
                }
                case 3 -> carrinho.adicionarAoCarrinhoPorId();
                case 4 -> carrinho.verCarrinho();
                case 5 -> carrinho.finalizarCompra();
                case 6 -> {
                    System.out.println("Saindo da aplicação... Até logo!");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("==============================");
        }
    }
}
