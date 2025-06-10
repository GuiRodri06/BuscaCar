package model.servicos;

import model.entities.carros.carrosCondi;

import java.util.List;

public interface InterfaceCarroDAO {

    int cadastrarMarca(String nomeMarca);
    int cadastrarModelo(String nomeModelo, int idMarca);
    void cadastrarCarro(int idModelo, int ano, double preco, int km, String combustivel);
    void listarTodosCarros();
    void deletarCarroPorId(int idCarro);
    List<carrosCondi> listarCarrosComoObjetos();
    void imprimirCarrosCondicionados();
}
