package model.entities.carros;

public class Jeep extends carrosCondi {

    public Jeep(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra, String cor) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Jeep [modelo=" + getModelo() + "]";
    }
    
    
}
