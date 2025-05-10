package model.entities.carros;

public class Ford extends carrosCondi {

    public Ford(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Ford [modelo=" + getModelo() + "]";
    }
    
}
