package model.entities.carros;

public class Fiat extends carrosCondi {

    public Fiat(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Fiat [modelo=" + getModelo() + "]";
    }
    
}
