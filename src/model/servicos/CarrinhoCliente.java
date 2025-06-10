package model.servicos;

import model.entities.carros.carrosCondi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Importa a classe Scanner para entrada de dados
import java.util.List;
import java.util.ArrayList;

public class CarrinhoCliente {

    // Lista que representa o carrinho de compras (carros selecionados pelo cliente)
    private List<carrosCondi> carrinho = new ArrayList<>();

    // Método que permite ao cliente adicionar um carro ao carrinho informando o ID
    public void adicionarAoCarrinhoPorId() {
        CarroDAO dao = new CarroDAO(); // DAO para acessar os dados dos carros
        List<carrosCondi> listaCarros = dao.listarCarrosComoObjetos(); // Lista de carros disponíveis

        // Exibe os carros com seus IDs para que o cliente possa escolher
        dao.imprimirCarrosCondicionados();

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ID do carro que deseja adicionar ao carrinho: ");
        int idDesejado = sc.nextInt(); // Lê o ID digitado pelo cliente

        carrosCondi selecionado = null;

        // Procura na lista o carro que corresponde ao ID informado
        for (carrosCondi c : listaCarros) {
            if (c.getId() == idDesejado) {
                selecionado = c;
                break;
            }
        }

        // Se o carro foi encontrado, adiciona ao carrinho
        if (selecionado != null) {
            carrinho.add(selecionado);
            System.out.println("Carro adicionado ao carrinho com sucesso!");
        } else {
            // Caso o ID não exista, exibe mensagem de erro
            System.out.println("ID não encontrado. Tente novamente.");
        }
    }

    // Método que exibe os carros que estão atualmente no carrinho
    public void verCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho está vazio.");
            return; // Encerra o método se não há itens no carrinho
        }

        System.out.println("======= CARRINHO DO CLIENTE =======");
        for (carrosCondi c : carrinho) {
            // Exibe informações detalhadas sobre cada carro no carrinho
            System.out.printf("ID: %d | Modelo: %-10s | Submodelo: %-12s | Ano: %d | Km: %d | Combustível: %-9s | Tarifa €/dia: %.2f%n",
                    c.getId(), c.getModelo(), c.getSubmodelo(), c.getAno(), c.getKm(), c.getConbustivel(), c.getTarifaDiaBase());
        }
    }

    // Método para simular a finalização da compra (aluguel dos carros)
    public void finalizarCompra() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio. Nada para finalizar.");
            return; // Encerra o método se não há carros no carrinho
        }

        Scanner sc = new Scanner(System.in);
        double total = 0.0;

        System.out.println("===== FINALIZAR COMPRA =====");
        for (carrosCondi c : carrinho) {
            // Exibe informações do carro
            System.out.printf("Carro: %s %s | Tarifa base/dia: %.2f €\n",
                    c.getModelo(), c.getSubmodelo(), c.getTarifaDiaBase());

            // Solicita a quantidade de dias de aluguel para o carro
            System.out.print("Quantos dias deseja alugar este carro? ");
            int dias = sc.nextInt();

            // Aplica desconto para aluguel de longa duração (regra definida na classe carrosCondi)
            c.calcularDescontoLongaDuracao(dias);

            // Calcula o subtotal para esse carro e soma ao total geral
            double subtotal = c.getTarifaDiaBase() * dias;
            total += subtotal;

            System.out.printf("Subtotal para este carro: %.2f €\n\n", subtotal);
        }

        // Exibe o valor total da compra
        System.out.printf("Valor total da compra: %.2f €\n", total);
        System.out.println("Compra finalizada com sucesso. Obrigado!");

        carrinho.clear(); // Esvazia o carrinho após finalização
    }
}