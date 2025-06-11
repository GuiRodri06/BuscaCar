//Guilherme Lucas
package model.servicos;

public class HomePage {

    public static void Home() {

        try {
            System.out.println("----------------- BEM-VINDO AO BUSCACAR -----------------");
            Thread.sleep(500);  // pausa de 0.5 segundos
            System.out.println("\nSomos uma empresa criada pelos desenvolvedores de software Guilherme Rodrigues e Guilherme Lucas!");
            Thread.sleep(1000);  // pausa de 1 segundo
            System.out.println("Nosso objetivo e entregar uma qualidade excelente e inovadora no sistema de locadora de carros com uma aplicacao intuitiva e funcional!\n");
            Thread.sleep(1000); // pausa de 1 segundo
            System.out.println("\"Temos mais de 150 carros disponíveis, com opções para todos os gostos e bolsos — desde modelos desportivos para momentos de lazer até viaturas familiares e acessíveis.\n" +
                    "\n");
            Thread.sleep(1000);  // pausa de 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace(); // trata possíveis erros
        }
    }
}
