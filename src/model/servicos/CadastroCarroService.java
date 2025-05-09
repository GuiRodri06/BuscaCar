package model.servicos;

import java.util.Scanner;

public class CadastroCarroService {

    public static void executarCadastro() {
        Scanner scanner = new Scanner(System.in);
        CarroDAO dao = new CarroDAO();

        System.out.println("========== CADASTRO DE CARRO ===========");

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        int idMarca = dao.cadastrarMarca(marca);
        int idModelo = dao.cadastrarModelo(modelo, idMarca);
        dao.cadastrarCarro(idModelo, ano, preco);
    }
}
