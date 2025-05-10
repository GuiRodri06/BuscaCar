package model.entities.carros;

public class Kia extends carrosCondi {

    public Kia(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Kia [modelo=" + getModelo() + "]";
    }
    
}
