package model.servicos;

import model.entities.carros.carrosCondi;

import java.util.List;
import java.util.Scanner;

public class AdicionarCarrinhoService {

    public static void executarAdicaoAoCarrinho(CarroDAO daocar) {
        Scanner txt = new Scanner(System.in);

        List<carrosCondi> lista = daocar.listarCarrosComoObjetos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum carro disponível para adicionar.");
            return;
        }

        System.out.println("========= ESCOLHA UM CARRO =========");
        daocar.imprimirCarrosCondicionados();

        System.out.print("Digite o nome do modelo exatamente como aparece: ");
        String modeloEscolhido = txt.nextLine().trim();

        carrosCondi carroSelecionado = lista.stream()
                .filter(c -> c.getModelo().equalsIgnoreCase(modeloEscolhido))
                .findFirst()
                .orElse(null);

        if (carroSelecionado != null) {
            CarrinhoService.adicionarAoCarrinho(carroSelecionado);
        } else {
            System.out.println("Carro não encontrado! Verifique o nome e tente novamente.");
        }
    }
}
