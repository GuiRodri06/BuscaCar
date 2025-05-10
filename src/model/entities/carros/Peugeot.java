package model.entities.carros;

public class Peugeot extends carrosCondi {

    public Peugeot(String modelo, String submodelo, int km, int ano, String conbustivel, int preçoCompra) {
        super(modelo, submodelo, km, ano, conbustivel, preçoCompra);
    }

    @Override
    public String toString() {
        return "Peugeot [modelo=" + getModelo() + "]";
    }
    
}
