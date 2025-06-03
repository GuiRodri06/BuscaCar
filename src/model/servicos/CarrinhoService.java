package model.servicos;

import model.entities.carros.carrosCondi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarrinhoService {
    private static List<carrosCondi> carrinho = new ArrayList<>();

    public static void adicionarAoCarrinho(carrosCondi carro) {
        carrinho.add(carro);
        System.out.println("Carro adicionado ao carrinho com sucesso!");
    }

    public static void visualizarCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho está vazio.");
            return;
        }

        System.out.println("========= SEU CARRINHO =========");
        double total = 0.0;
        for (carrosCondi c : carrinho) {
            System.out.printf("Modelo: %s | Submodelo: %s | Ano: %d | Tarifa/dia: €%.2f%n",
                    c.getModelo(), c.getSubmodelo(), c.getAno(), c.getTarifaDiaBase());
            total += c.getTarifaDiaBase();
        }
        System.out.printf("Tarifa total estimada: €%.2f%n", total);
    }

    public static void finalizarAluguel() {
        if (carrinho.isEmpty()) {
            System.out.println("Você não possui nenhum carro no carrinho para alugar.");
            return;
        }

        visualizarCarrinho();
        System.out.println("Confirmar aluguel? (s/n): ");
        Scanner sc = new Scanner(System.in);
        String resp = sc.nextLine().trim().toLowerCase();

        if (resp.equals("s")) {
            System.out.println("Aluguel finalizado com sucesso! Aproveite o carro.");
            carrinho.clear();
        } else {
            System.out.println("Aluguel cancelado.");
        }
    }
}
