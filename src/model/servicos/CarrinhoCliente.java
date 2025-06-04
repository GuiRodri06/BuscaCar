package model.servicos;

import model.entities.carros.carrosCondi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarrinhoCliente {

    private List<carrosCondi> carrinho = new ArrayList<>();

    public void adicionarAoCarrinhoPorId() {
        CarroDAO dao = new CarroDAO();
        List<carrosCondi> listaCarros = dao.listarCarrosComoObjetos();

        // Mostra os carros com ID chamando o método existente
        dao.imprimirCarrosCondicionados();

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ID do carro que deseja adicionar ao carrinho: ");
        int idDesejado = sc.nextInt();

        carrosCondi selecionado = null;

        for (carrosCondi c : listaCarros) {
            if (c.getId() == idDesejado) {
                selecionado = c;
                break;
            }
        }

        if (selecionado != null) {
            carrinho.add(selecionado);
            System.out.println("Carro adicionado ao carrinho com sucesso!");
        } else {
            System.out.println("ID não encontrado. Tente novamente.");
        }
    }


    public void verCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho está vazio.");
            return;
        }

        System.out.println("======= CARRINHO DO CLIENTE =======");
        for (carrosCondi c : carrinho) {
            System.out.printf("ID: %d | Modelo: %-10s | Submodelo: %-12s | Ano: %d | Km: %d | Combustível: %-9s | Tarifa €/dia: %.2f%n",
                    c.getId(), c.getModelo(), c.getSubmodelo(), c.getAno(), c.getKm(), c.getConbustivel(), c.getTarifaDiaBase());
        }
    }

    public void finalizarCompra() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio. Nada para finalizar.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        double total = 0.0;

        System.out.println("===== FINALIZAR COMPRA =====");
        for (carrosCondi c : carrinho) {
            System.out.printf("Carro: %s %s | Tarifa base/dia: %.2f €\n",
                    c.getModelo(), c.getSubmodelo(), c.getTarifaDiaBase());

            System.out.print("Quantos dias deseja alugar este carro? ");
            int dias = sc.nextInt();

            c.calcularDescontoLongaDuracao(dias);
            double subtotal = c.getTarifaDiaBase() * dias;
            total += subtotal;

            System.out.printf("Subtotal para este carro: %.2f €\n\n", subtotal);
        }

        System.out.printf("Valor total da compra: %.2f €\n", total);
        System.out.println("Compra finalizada com sucesso. Obrigado!");

        carrinho.clear(); // limpa o carrinho após a compra
    }
}
