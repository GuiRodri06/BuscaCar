package model.entities.carros;

public class BMW extends carrosCondi {

    public BMW(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "BMW [modelo=" + getModelo() + "]";
    }
    
}
