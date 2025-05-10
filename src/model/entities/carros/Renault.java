package model.entities.carros;

public class Renault extends carrosCondi {

    public Renault(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Renault [modelo=" + getModelo() + "]";
    }
    
}
